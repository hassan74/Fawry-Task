package com.task.ui.component.movie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.task.data.dto.movies.MovieItem
import com.task.databinding.MovieItemBinding
import com.task.ui.base.listeners.MovieItemListener

class MovieAdapter(
    private var movies: ArrayList<MovieItem>,
    private val movieItemListener: MovieItemListener
) : RecyclerView.Adapter<MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemBinding =
            MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position]) { selectedMovie ->
            movieItemListener.onMovieSelected(selectedMovie)
        }
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}

