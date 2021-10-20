package com.example.flow.recycler_view

import androidx.recyclerview.widget.DiffUtil
import com.example.flow.db.movies.Movie
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class MovieListAdapter : AsyncListDifferDelegationAdapter<Movie>(MovieDiffUtilCallback()) {

    init {
        delegatesManager.addDelegate(MovieListDelegate())
    }

    private class MovieDiffUtilCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }
}