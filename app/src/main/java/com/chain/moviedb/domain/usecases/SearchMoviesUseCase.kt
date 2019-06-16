package com.chain.moviedb.domain.usecases

import com.chain.moviedb.domain.entities.Movie
import io.reactivex.Single

class SearchMoviesUseCase {

    fun searchMovies(query: String) : Single<List<Movie>>{
        TODO()
    }

}