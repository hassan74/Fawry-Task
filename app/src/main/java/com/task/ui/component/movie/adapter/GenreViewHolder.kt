package com.task.ui.component.movie.adapter

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.task.data.dto.movies.MovieItem
import com.task.data.dto.movies.Movies
import com.task.databinding.GenreItemBinding
import com.task.ui.base.listeners.RecyclerItemListener
import com.task.ui.component.movie.MovieViewModel


class GenreViewHolder(private val itemBinding: GenreItemBinding ,private val movieViewModel: MovieViewModel) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(genre: Movies) {
        itemBinding.tvGenre.text = genre.genre
        itemBinding.rvMovies.layoutManager=LinearLayoutManager(itemBinding.genreItem.context )
        itemBinding.tvGenre.text = genre.genre
        itemBinding.rvMovies.layoutManager=LinearLayoutManager(itemBinding.genreItem.context  ,LinearLayoutManager.HORIZONTAL ,false)
        itemBinding.rvMovies.adapter =MovieAdapter(movieViewModel ,
            genre.movies as ArrayList<MovieItem>
        )
    }
}

