package com.chain.moviedb.presentation.moviedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chain.moviedb.Injector
import com.chain.moviedb.databinding.MovieDetailFragmentBinding
import com.chain.moviedb.di.ViewModelFactory
import com.chain.moviedb.presentation.common.BaseFragment
import com.chain.moviedb.util.movieIdKey
import javax.inject.Inject

class MovieDetailFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val movieDetailViewModel: MovieDetailViewModel by lazy {
        viewModelFactory.getFragmentScopedViewModel(MovieDetailViewModel::class)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Injector.get().injectInto(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = MovieDetailFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = movieDetailViewModel

        arguments?.run {
            val movieId = getInt(movieIdKey)
            movieDetailViewModel.loadMovie(movieId)
        }

        return binding.root
    }
}