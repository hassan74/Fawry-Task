package com.task.ui.base.listeners

import com.task.data.dto.movies.MovieItem


interface MovieItemListener {
    fun onMovieSelected(movie:MovieItem)
}
