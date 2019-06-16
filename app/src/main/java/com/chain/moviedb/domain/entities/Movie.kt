package com.chain.moviedb.domain.entities

data class Movie(
    val id: Int,
    val title: String,
    val vote: Double,
    val description: String,
    val posterUrl: String
)