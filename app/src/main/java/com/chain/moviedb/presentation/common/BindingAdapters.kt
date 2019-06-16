package com.chain.moviedb.presentation.common

import android.util.Log
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