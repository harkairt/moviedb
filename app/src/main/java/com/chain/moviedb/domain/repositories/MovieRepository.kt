package com.chain.moviedb.domain.repositories

import com.chain.moviedb.domain.entities.Movie
import io.reactivex.Single

interface MovieRepository {

    fun searchMovies(query: String) : Single<List<Movie>>

    fun getMovieDetails(movieId: Int) : Single<Movie>

}