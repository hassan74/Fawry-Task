package com.task.ui.component.movie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.task.data.dto.movies.MovieItem
import com.task.data.dto.movies.Movies
import com.task.databinding.GenreItemBinding
import com.task.databinding.MovieItemBinding
import com.task.ui.base.listeners.RecyclerItemListener
import com.task.ui.component.movie.MovieViewModel


class MovieAdapter(private val movieViewModel: MovieViewModel, private var movies: ArrayList<MovieItem>)
    : RecyclerView.Adapter<MovieViewHolder>()  {


    private val onItemClickListener: RecyclerItemListener = object : RecyclerItemListener {
        override fun onItemSelected(movie: MovieItem) {
         //   TODO("Not yet implemented")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemBinding = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position], onItemClickListener)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

}

