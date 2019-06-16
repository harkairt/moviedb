package com.chain.moviedb.presentation.common

import androidx.recyclerview.widget.RecyclerView
import com.jakewharton.rxbinding3.view.clicks
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

abstract class ClickableAdapter<VH, T> : RecyclerView.Adapter<VH>() where VH : RecyclerView.ViewHolder {
    private val clickSubject = PublishSubject.create<T>()
    val clickEvents: Observable<T> = clickSubject

    abstract fun getDataSource(): List<T>

    override fun getItemCount(): Int = getDataSource().count()

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.itemView.clicks().map { getDataSource()[holder.adapterPosition] }.subscribe(clickSubject)
    }
}