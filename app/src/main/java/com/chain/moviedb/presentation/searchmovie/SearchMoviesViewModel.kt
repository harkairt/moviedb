package com.chain.moviedb.presentation.searchmovie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.chain.moviedb.domain.entities.Movie
import com.chain.moviedb.domain.usecases.SearchMoviesUseCase
import com.chain.moviedb.presentation.common.DisposingViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SearchMoviesViewModel @Inject constructor(private val searchMoviesUseCase: SearchMoviesUseCase) : DisposingViewModel() {

    private val _movieSearchResult: MutableLiveData<MovieSearchResult> = MutableLiveData()
    val movieSearchResult: LiveData<MovieSearchResult> = _movieSearchResult

    fun search(query: String) {
        searchMoviesUseCase.searchMovies(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _movieSearchResult.postValue(MovieSearchResult.success(it))
                }, {
                    _movieSearchResult.postValue(MovieSearchResult.error())
                }
                ).disposeOnCleared()
    }

}

enum class MovieSearchResultType {
    SUCCESS,
    EMPTY,
    ERROR
}

class MovieSearchResult private constructor(val movieSearchResultType: MovieSearchResultType, val movieList: List<Movie> = listOf()) {
    companion object {

        fun success(movieList: List<Movie>): MovieSearchResult {
            return if (movieList.any())
                MovieSearchResult(MovieSearchResultType.SUCCESS, movieList)
            else
                MovieSearchResult(MovieSearchResultType.EMPTY)
        }

        fun error() = MovieSearchResult(MovieSearchResultType.ERROR)
    }
}