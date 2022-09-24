package com.task.ui.component.movie.adapter

import androidx.recyclerview.widget.RecyclerView
import com.task.IMAGE_URL
import com.task.data.dto.movies.MovieItem
import com.task.databinding.MovieItemBinding
import com.task.utils.loadImage


class MovieViewHolder(private val itemBinding: MovieItemBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(movie: MovieItem, callBack :(item :MovieItem) -> Unit) {
        itemBinding.tvTitle.text = movie.name
        itemBinding.ivMovie.loadImage(IMAGE_URL+movie.posterPath)
        itemBinding.movieItem.setOnClickListener { callBack(movie) }
    }
}

