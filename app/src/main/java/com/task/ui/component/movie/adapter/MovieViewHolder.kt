package com.task.ui.component.movie.adapter

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.task.data.dto.movies.MovieItem
import com.task.data.dto.movies.Movies
import com.task.databinding.GenreItemBinding
import com.task.databinding.MovieItemBinding
import com.task.ui.base.listeners.RecyclerItemListener
import com.task.utils.loadImage


class MovieViewHolder(private val itemBinding: MovieItemBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(movie: MovieItem, recyclerItemListener: RecyclerItemListener) {
        itemBinding.tvTitle.text = movie.name
        itemBinding.ivMovie.loadImage("https://image.tmdb.org/t/p/w185"+movie.posterPath)
        itemBinding.movieItem.setOnClickListener { recyclerItemListener.onItemSelected(movie) }
    }
}

