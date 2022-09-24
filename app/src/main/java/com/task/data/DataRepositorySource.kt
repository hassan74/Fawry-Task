package com.task.data

import com.task.data.dto.movies.Genres
import com.task.data.dto.movies.MovieItem
import com.task.data.dto.movies.Movies
import kotlinx.coroutines.flow.Flow

 

interface DataRepositorySource {
    suspend fun requestGenres(): Flow<Resource<Genres>>
    suspend fun requestMovieDetails(movieId:String): Flow<Resource<MovieItem>>
    suspend fun requestMovies(genres:Genres): Flow<Resource<Movies>>
}
