package com.task.data.remote

import com.task.data.Resource
import com.task.data.dto.movies.Genres
import com.task.data.dto.movies.MovieItem
import com.task.data.dto.movies.Movies


internal interface RemoteDataSource {
    suspend fun requestGenres(): Resource<Genres>
    suspend fun requestMovieDetails(movieId:String): Resource<MovieItem>
    suspend fun requestMovies(genre:String): Resource<Movies>
}
