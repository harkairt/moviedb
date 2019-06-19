package com.chain.moviedb.di

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(dependencies = [ViewModelComponent::class])
interface ApplicationComponent {
    fun viewModelFactory() : ViewModelFactory
}