package com.chain.moviedb.presentation.searchmovie

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.chain.moviedb.R
import com.chain.moviedb.databinding.MovieListItemViewBinding
import com.chain.moviedb.domain.entities.Movie
import com.chain.moviedb.presentation.common.BaseAdapter
import com.chain.moviedb.util.posterBasePath
import com.squareup.picasso.Picasso

class MovieAdapter : BaseAdapter<MovieItemViewHolder, Movie>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder =
        MovieItemViewHolder(MovieListItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.bind(getDataSource()[position])
    }
}

class MovieItemViewHolder(private val itemViewBinding: MovieListItemViewBinding) : RecyclerView.ViewHolder(itemViewBinding.root) {
    private val moviePosterView : ImageView = itemView.findViewById(R.id.moviePosterView)

    fun bind(movie: Movie) {
        itemViewBinding.movie = movie
        Picasso.get()
            .load("$posterBasePath${movie.poster_path}")
            .fit()
            .into(moviePosterView)
    }
}