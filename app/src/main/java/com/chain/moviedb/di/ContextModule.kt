package com.chain.moviedb.di

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ContextModule(private val context : Context){
    @Provides
    fun appContext(): Context = context
}
