package com.chain.moviedb.domain.entities

data class Movie(
    val id: Int,
    val title: String,
    val vote_average: Double,
    val vote_count: Int,
    val overview: String,
    val budget: Int,
    val poster_path: String
)