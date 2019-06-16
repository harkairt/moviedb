package com.chain.moviedb.data

import com.chain.moviedb.domain.entities.Movie

data class MovieDBSearchResponse (
        val results: List<Movie>
)