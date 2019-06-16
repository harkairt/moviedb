package com.chain.moviedb.presentation.searchmovie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chain.moviedb.Injector
import com.chain.moviedb.R
import com.chain.moviedb.databinding.SearchMoviesFragmentBinding
import com.chain.moviedb.di.ViewModelFactory
import com.chain.moviedb.domain.entities.Movie
import com.chain.moviedb.presentation.common.BaseFragment
import com.chain.moviedb.presentation.common.onQueryTextSubmitted
import com.chain.moviedb.util.movieIdKey
import javax.inject.Inject

class SearchMoviesFragment : BaseFragment(){

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val searchMoviesViewModel: SearchMoviesViewModel by lazy {
        viewModelFactory.getFragmentScopedViewModel(SearchMoviesViewModel::class)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Injector.get().injectInto(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = SearchMoviesFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = searchMoviesViewModel

        setupRecyclerView(binding.root){
            findNavController().navigate(R.id.movieDetailsNavigationAction, Bundle().apply {
                putInt(movieIdKey, it.id)
            })
        }

        val searchView: SearchView = binding.root.findViewById(R.id.moviesSearchView)
        searchView.onQueryTextSubmitted {
            searchMoviesViewModel.search(it)
        }

        return binding.root
    }


    private fun setupRecyclerView(root: View, onItemClick: ((Movie) -> Unit) = {}) {
        root.findViewById<RecyclerView>(R.id.movieSearchResultRecyclerView).run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@SearchMoviesFragment.context)
            adapter = MovieAdapter().apply {
                clickEvents.subscribe { onItemClick(it) }.disposeOnDestroy()
            }
            addItemDecoration(
                    DividerItemDecoration(
                            this@SearchMoviesFragment.context,
                            DividerItemDecoration.VERTICAL
                    )
            )
        }
    }

}