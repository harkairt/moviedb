package com.chain.moviedb.presentation.common

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.chain.moviedb.di.ViewModelFactory
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlin.reflect.KClass

abstract class BaseFragment : Fragment() {
    private val bag = CompositeDisposable()

    override fun onDestroy() {
        bag.clear()
        super.onDestroy()
    }

    fun Disposable.disposeOnDestroy() {
        bag.add(this)
    }

    protected fun <T : ViewModel> ViewModelFactory.getFragmentScopedViewModel(viewModelKClass: KClass<T>): T {
        return androidx.lifecycle.ViewModelProviders.of(this@BaseFragment, this).get(viewModelKClass.java)
    }
}