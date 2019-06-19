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
import com.chain.moviedb.domain.entities.Movie
import com.chain.moviedb.presentation.common.BaseFragment
import com.chain.moviedb.presentation.common.onQueryTextSubmitted
import com.chain.moviedb.util.movieIdKey

class SearchMoviesFragment : BaseFragment() {

    private val searchMoviesViewModel: SearchMoviesViewModel by lazy {
        val viewModelFactory = Injector.get().viewModelFactory()
        viewModelFactory.getFragmentScopedViewModel(SearchMoviesViewModel::class)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = SearchMoviesFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = searchMoviesViewModel

        setupSearchView(binding.root, onSearch = searchMoviesViewModel::search)
        setupMoviesRecyclerView(binding.root, onClick = ::navigateToDetail)

        return binding.root
    }


    private fun setupSearchView(root: View, onSearch: (String) -> Unit) {
        val searchView: SearchView = root.findViewById(R.id.moviesSearchView)
        searchView.onQueryTextSubmitted {
            onSearch.invoke(it)
        }
    }

    private fun setupMoviesRecyclerView(root: View, onClick: (Movie) -> Unit) {
        root.findViewById<RecyclerView>(R.id.movieSearchResultRecyclerView).run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@SearchMoviesFragment.context)
            adapter = MovieAdapter {
                onClickAction = onClick
            }
            addItemDecoration(
                DividerItemDecoration(
                    this@SearchMoviesFragment.context,
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }

    private fun navigateToDetail(it: Movie) {
        findNavController().navigate(R.id.movieDetailsNavigationAction, Bundle().apply {
            putInt(movieIdKey, it.id)
        })
    }


}