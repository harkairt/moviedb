package com.chain.moviedb.di

import com.chain.moviedb.data.webservice.TheMovieDBWebservice
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory


@Module
class ServiceModule {

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://api.themoviedb.org/3/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Provides
    fun movieWebservice(): TheMovieDBWebservice {
        return retrofit.create<TheMovieDBWebservice>(TheMovieDBWebservice::class.java)
    }

}