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

    private val _movieSearchResult: MutableLiveData<MovieSearchResult> = MutableLiveData()
    val movieSearchResult: LiveData<MovieSearchResult> = _movieSearchResult

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
                _movieSearchResult.postValue(MovieSearchResult.success(it))
            }, {
                _movieSearchResult.postValue(MovieSearchResult.error())
            }
            ).disposeOnCleared()
    }

}

enum class ResultType {
    SUCCESS,
    EMPTY,
    ERROR
}

class MovieSearchResult private constructor(val type: ResultType, val movieList: List<Movie> = listOf()) {
    companion object {
        private val emptySearchResult = MovieSearchResult(ResultType.EMPTY)
        private val errorSearchResult = MovieSearchResult(ResultType.ERROR)

        fun success(movieList: List<Movie>): MovieSearchResult {
            return if (movieList.any())
                MovieSearchResult(ResultType.SUCCESS, movieList)
            else
                emptySearchResult
        }

        fun error() = errorSearchResult
    }
}