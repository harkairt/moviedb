package com.chain.moviedb.presentation.searchmovie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chain.moviedb.databinding.MovieListItemViewBinding
import com.chain.moviedb.domain.entities.Movie
import com.chain.moviedb.presentation.common.BaseAdapter

class MovieAdapter(init : MovieAdapter.() -> Unit) : BaseAdapter<MovieItemViewHolder, Movie>() {
    init {
        init()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder {
        return MovieItemViewHolder(MovieListItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.bind(getDataSource()[position])
    }
}

class MovieItemViewHolder(private val itemViewBinding: MovieListItemViewBinding) : RecyclerView.ViewHolder(itemViewBinding.root) {
    fun bind(movie: Movie) {
        itemViewBinding.movie = movie
    }
}