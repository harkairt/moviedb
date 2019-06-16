package com.chain.moviedb.presentation.common

import android.widget.SearchView


fun SearchView.onQueryTextSubmitted(action: (String) -> Unit){
    this.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
        override fun onQueryTextChange(newText: String?): Boolean = false

        override fun onQueryTextSubmit(query: String?): Boolean {
            query?.let {
                action(query)
                return true
            }
            return false
        }
    })
}