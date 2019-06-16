package com.chain.moviedb.domain.usecases

import com.chain.moviedb.domain.entities.Movie
import com.chain.moviedb.domain.repositories.MovieRepository
import io.reactivex.Single
import javax.inject.Inject

class MovieDetailUseCase @Inject constructor(private val movieRepository: MovieRepository) {

    fun getMovieDetails(movieId: Int) : Single<Movie> = movieRepository.getMovieDetails(movieId)

}