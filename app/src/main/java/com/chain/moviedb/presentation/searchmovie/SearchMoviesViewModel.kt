package com.chain.moviedb.presentation.searchmovie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.chain.moviedb.domain.entities.Movie
import com.chain.moviedb.domain.usecases.SearchMoviesUseCase
import com.chain.moviedb.presentation.common.DisposingViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SearchMoviesViewModel @Inject constructor(private val searchMoviesUseCase: SearchMoviesUseCase) :
    DisposingViewModel() {

    private val _movieSearchState: MutableLiveData<MovieSearchState> = MutableLiveData()
    val movieSearchState: LiveData<MovieSearchState> = _movieSearchState

    private val _loadingInProgress: MutableLiveData<Boolean> = MutableLiveData()
    val loadingInProgress: LiveData<Boolean> = _loadingInProgress

    fun search(query: String) {
        _loadingInProgress.postValue(true)

        searchMoviesUseCase.searchMovies(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally {
                _loadingInProgress.postValue(false)
            }
            .subscribe({
                _movieSearchState.postValue(MovieSearchState.success(it))
            }, {
                _movieSearchState.postValue(MovieSearchState.error())
            }
            ).disposeOnCleared()
    }

}

enum class MovieSearchStateType {
    SUCCESS,
    EMPTY,
    ERROR
}

class MovieSearchState private constructor(
        val movieSearchStateType: MovieSearchStateType,
        val movieList: List<Movie> = listOf()
) {
    companion object {
        private val emptySearchResultState = MovieSearchState(MovieSearchStateType.EMPTY)
        private val errorSearchResultState = MovieSearchState(MovieSearchStateType.ERROR)

        fun success(movieList: List<Movie>): MovieSearchState {
            return if (movieList.any())
                MovieSearchState(MovieSearchStateType.SUCCESS, movieList)
            else
                emptySearchResultState
        }

        fun error() = errorSearchResultState
    }
}