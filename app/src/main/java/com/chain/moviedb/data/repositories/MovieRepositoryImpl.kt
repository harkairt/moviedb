package com.chain.moviedb.data.repositories

import com.chain.moviedb.data.webservice.TheMovieDBWebservice
import com.chain.moviedb.domain.entities.Movie
import com.chain.moviedb.domain.repositories.MovieRepository
import io.reactivex.Single
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val movieWebservice: TheMovieDBWebservice) : MovieRepository {

    override fun searchMovies(query: String): Single<List<Movie>> {
        return movieWebservice.searchMovies(query).map { it.results }
    }

    override fun getMovieDetails(movieId: Int): Single<Movie> {
        return movieWebservice.getMovieDetails(movieId)
    }

}