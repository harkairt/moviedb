package com.chain.moviedb

import android.app.Application
import com.chain.moviedb.di.ApplicationComponent
import com.chain.moviedb.di.ContextModule
import com.chain.moviedb.di.DaggerApplicationComponent
import com.chain.moviedb.di.DaggerViewModelComponent

class MainApplication : Application() {
    lateinit var component: ApplicationComponent

    companion object {
        private var INSTANCE: MainApplication? = null

        @JvmStatic
        fun get(): MainApplication = INSTANCE!!
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this

        component = initDagger()
    }

    private fun initDagger(): ApplicationComponent {
        return DaggerApplicationComponent.builder()
            .viewModelComponent(
                DaggerViewModelComponent
                    .builder()
                    .contextModule(ContextModule(this))
                    .build()
            )
            .build()
    }
}

class Injector private constructor() {
    companion object {
        fun get(): ApplicationComponent = MainApplication.get().component
    }
}