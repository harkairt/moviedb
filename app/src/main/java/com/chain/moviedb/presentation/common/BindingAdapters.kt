package com.chain.moviedb.presentation.common

import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("data")
fun <T> setData(recyclerView: RecyclerView, data: T) {
    if (data == null) {
        Log.e("____", "recyclerView data binding: data is null!")
        return
    }

    if (recyclerView.adapter is BindableAdapter<*>)
        (recyclerView.adapter as BindableAdapter<T>).bindData(data)
}


@BindingAdapter("in_progress")
fun <T> setInProgress(progressBar: ProgressBar, inProgress: Boolean) {
    progressBar.visibility = if (inProgress) View.VISIBLE else View.INVISIBLE

}