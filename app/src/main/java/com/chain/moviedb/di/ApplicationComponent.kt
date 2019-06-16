package com.chain.moviedb.di

import com.chain.moviedb.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(dependencies = [ViewModelComponent::class])
interface ApplicationComponent {
    fun injectInto(mainActivity: MainActivity)
}