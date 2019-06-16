package com.chain.moviedb.data.webservice

import com.chain.moviedb.data.MovieDBSearchResponse
import com.chain.moviedb.domain.entities.Movie
import com.chain.moviedb.util.apiKey
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMovieDBWebservice {

    @GET("search/movie?api_key=$apiKey")
    fun searchMovies(@Query("query") query: String) : Single<MovieDBSearchResponse>


    @GET("movie/{movieId}?api_key=$apiKey")
    fun getMovieDetails(@Path("movieId") movieId: Int) : Single<Movie>

}