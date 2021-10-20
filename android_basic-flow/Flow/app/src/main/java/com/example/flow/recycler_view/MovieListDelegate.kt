package com.example.flow.recycler_view

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flow.R
import com.example.flow.databinding.ItemMovieBinding
import com.example.flow.db.movies.Movie
import com.example.flow.utils.inflate
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate

class MovieListDelegate : AbsListItemAdapterDelegate<Movie, Movie, MovieListDelegate.Holder>() {


    override fun isForViewType(item: Movie, items: MutableList<Movie>, position: Int): Boolean {
        return true
    }

    override fun onCreateViewHolder(parent: ViewGroup): Holder {
        return Holder(parent.inflate(R.layout.item_movie))
    }

    override fun onBindViewHolder(item: Movie, holder: Holder, payloads: MutableList<Any>) {
        holder.bind(item)
    }


    class Holder(containerView: View) : RecyclerView.ViewHolder(containerView) {
        private val binding = ItemMovieBinding.bind(containerView)

        fun bind(movie: Movie) {
            with(binding) {
                textViewMovieName.text = movie.title

                Glide.with(itemView)
                    .load(movie.poster)
                    .placeholder(R.drawable.ic_access)
                    .error(R.drawable.ic_error)
                    .into(imageViewPoster)
            }
        }
    }
}