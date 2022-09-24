package com.task.data.remote.service

import com.task.data.dto.movies.Genres
import com.task.data.dto.movies.Movies
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap


interface MovieService {
    @GET("3/genre/movie/list?api_key=c2a845daa01463fbf94d9b12347c88e1")
    suspend fun fetchGenres(
        @QueryMap options: Map<String, String>? =null
    ): Response<Genres>

    @GET("3/discover/movie?api_key=c2a845daa01463fbf94d9b12347c88e1")
    suspend fun fetchMovies(
        @QueryMap options: Map<String, String>? =null
    ): Response<Movies>
}

