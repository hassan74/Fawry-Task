package com.task.ui.component.movie.adapter

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.task.data.dto.movies.MovieItem
import com.task.data.dto.movies.Movies
import com.task.databinding.GenreItemBinding
import com.task.ui.base.listeners.MovieItemListener


class GenreViewHolder(
    private val itemBinding: GenreItemBinding
) : RecyclerView.ViewHolder(itemBinding.root), MovieItemListener {

    private lateinit var movieCallBack: (movie: MovieItem) -> Unit

    fun bind(genre: Movies, callBack: (movieItem: MovieItem) -> Unit) {
        movieCallBack = callBack
        itemBinding.tvGenre.text = genre.genre
        itemBinding.rvMovies.layoutManager = LinearLayoutManager(itemBinding.genreItem.context)
        itemBinding.tvGenre.text = genre.genre
        itemBinding.rvMovies.layoutManager = LinearLayoutManager(
            itemBinding.genreItem.context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        itemBinding.rvMovies.adapter = MovieAdapter(
            genre.movies as ArrayList<MovieItem>,
            this
        )

    }

    override fun onMovieSelected(movie: MovieItem) {
        movieCallBack(
            movie
        )
    }


}

