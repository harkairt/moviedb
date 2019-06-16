package com.chain.moviedb.domain.usecases

import com.chain.moviedb.domain.entities.Movie
import com.chain.moviedb.domain.repositories.MovieRepository
import io.reactivex.Single
import javax.inject.Inject

class SearchMoviesUseCase @Inject constructor(private val movieRepository: MovieRepository) {

    fun searchMovies(query: String) : Single<List<Movie>> {
        return movieRepository.searchMovies(query)
            .flattenAsObservable { it }
            .map { movieRepository.getMovieDetails(it.id) }
            .flatMapSingle { it }
            .toList()
    }

}