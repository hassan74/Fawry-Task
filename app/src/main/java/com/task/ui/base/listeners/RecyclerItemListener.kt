package com.task.ui.base.listeners

import com.task.data.dto.movies.MovieItem


interface RecyclerItemListener {
    fun onItemSelected(movie:MovieItem)
}
