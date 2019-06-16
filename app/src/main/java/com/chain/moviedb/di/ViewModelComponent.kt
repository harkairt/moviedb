package com.chain.moviedb.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.chain.moviedb.presentation.moviedetail.MovieDetailViewModel
import com.chain.moviedb.presentation.searchmovie.SearchMoviesViewModel
import dagger.Binds
import dagger.Component
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Inject
import javax.inject.Provider
import kotlin.reflect.KClass

class ViewModelFactory @Inject constructor(private val viewModelProviders: Map<
        Class<out ViewModel>,
        @JvmSuppressWildcards Provider<ViewModel>>)
    : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return viewModelProviders[modelClass]?.get() as T
    }
}

@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value : KClass<out ViewModel>)

@Component(modules = [
    ViewModelModule::class,
    ContextModule::class,
    ServiceModule::class,
    RepositoryBindingModule::class
])
interface ViewModelComponent{
    fun viewModelProviders() : Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
}

@Module
interface ViewModelModule{

    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory) : ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(SearchMoviesViewModel::class)
    fun bindSearchMoviesViewModel(viewModel: SearchMoviesViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MovieDetailViewModel::class)
    fun bindMovieDetailViewModel(viewModel: MovieDetailViewModel) : ViewModel

}
