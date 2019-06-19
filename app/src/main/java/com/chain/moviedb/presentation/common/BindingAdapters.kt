package com.chain.moviedb.presentation.common

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.chain.moviedb.presentation.searchmovie.MovieSearchStateType
import com.chain.moviedb.util.posterBasePath
import com.squareup.picasso.Picasso

@BindingAdapter("data")
fun <T> setData(recyclerView: RecyclerView, data: T) {
    if (data == null) {
        Log.e("____", "recyclerView data binding: data is null!")
        return
    }

    if (recyclerView.adapter is BindableAdapter<*>)
        (recyclerView.adapter as BindableAdapter<T>).bindData(data)
}


@BindingAdapter("inProgress")
fun setInProgress(progressBar: ProgressBar, inProgress: Boolean) {
    progressBar.visibility = if (inProgress) View.VISIBLE else View.INVISIBLE

}

@BindingAdapter("posterPath")
fun loadPosterImage(imageView: ImageView, posterPath: String?) {
    posterPath?.let { nonNullPosterPath ->
        Picasso.get()
                .load("$posterBasePath$nonNullPosterPath")
                .fit()
                .into(imageView)
    }
}

@BindingAdapter("searchState")
fun setTextBasedOnResultType(textView: TextView, searchState: MovieSearchStateType?) {
    searchState?.let {
        textView.text = when (searchState) {
            MovieSearchStateType.EMPTY -> "No results found."
            MovieSearchStateType.ERROR -> "Something went wrong. :("
            else -> ""
        }
    }

}