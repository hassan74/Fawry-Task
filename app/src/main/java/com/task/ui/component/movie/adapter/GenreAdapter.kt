package com.task.ui.component.movie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.task.data.dto.movies.MovieItem
import com.task.data.dto.movies.Movies
import com.task.databinding.GenreItemBinding
import com.task.ui.base.listeners.RecyclerItemListener
import com.task.ui.component.movie.MovieViewModel


class GenreAdapter(private val movieViewModel: MovieViewModel, private var genres: ArrayList<Movies>)
    : RecyclerView.Adapter<GenreViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val itemBinding = GenreItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GenreViewHolder(itemBinding ,movieViewModel)
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.bind(genres[position])
    }

    override fun getItemCount(): Int {
        return genres.size
    }



}

