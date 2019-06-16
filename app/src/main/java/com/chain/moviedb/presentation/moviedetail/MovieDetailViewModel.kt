package com.chain.moviedb.presentation.moviedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.chain.moviedb.domain.entities.Movie
import com.chain.moviedb.domain.usecases.MovieDetailUseCase
import com.chain.moviedb.presentation.common.DisposingViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MovieDetailViewModel @Inject constructor(private val movieDetailUseCase: MovieDetailUseCase) : DisposingViewModel() {

    private val _movie: MutableLiveData<Movie> = MutableLiveData()
    val movie: LiveData<Movie> = _movie

    fun loadMovie(movieId: Int) {
        movieDetailUseCase.getMovieDetails(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { movie -> _movie.postValue(movie) }
                .disposeOnCleared()

    }
}