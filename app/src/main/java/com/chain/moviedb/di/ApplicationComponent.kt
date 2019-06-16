package com.chain.moviedb.di

import com.chain.moviedb.MainActivity
import com.chain.moviedb.presentation.moviedetail.MovieDetailFragment
import com.chain.moviedb.presentation.searchmovie.SearchMoviesFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(dependencies = [ViewModelComponent::class])
interface ApplicationComponent {
    fun injectInto(mainActivity: MainActivity)
    fun injectInto(searchMoviesFragment: SearchMoviesFragment)
    fun injectInto(movieDetailFragment: MovieDetailFragment)
}