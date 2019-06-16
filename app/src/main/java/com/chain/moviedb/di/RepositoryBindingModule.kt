package com.chain.moviedb.di

import com.chain.moviedb.data.repositories.MovieRepositoryImpl
import com.chain.moviedb.domain.repositories.MovieRepository
import dagger.Binds
import dagger.Module

@Module
interface RepositoryBindingModule {

    @Binds
    fun bindMovieRepository(movieRepositoryImpl: MovieRepositoryImpl) : MovieRepository

}