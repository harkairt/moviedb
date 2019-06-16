package com.chain.moviedb.domain.usecases

import com.chain.moviedb.domain.entities.Movie
import com.chain.moviedb.domain.repositories.MovieRepository
import io.reactivex.Single
import javax.inject.Inject

class SearchMoviesUseCase @Inject constructor(private val movieRepository: MovieRepository) {

    fun searchMovies(query: String) : Single<List<Movie>> = movieRepository.searchMovies(query)

}