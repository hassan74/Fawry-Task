package com.task.data.remote

import com.task.data.Resource
import com.task.data.dto.movies.GenreItem
import com.task.data.dto.movies.Genres
import com.task.data.dto.movies.MovieItem
import com.task.data.dto.movies.Movies
import com.task.data.error.NETWORK_ERROR
import com.task.data.error.NO_INTERNET_CONNECTION
import com.task.data.remote.service.MovieService
import com.task.utils.NetworkConnectivity
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject


class RemoteData @Inject
constructor(
    private val serviceGenerator: ServiceGenerator,
    private val networkConnectivity: NetworkConnectivity
) : RemoteDataSource {

    override suspend fun requestGenres(): Resource<Genres> {
        val genreService = serviceGenerator.createService(MovieService::class.java)
        val options = emptyMap<String ,String>()
        return when (val response =
            processCall(options, responseCall = genreService::fetchGenres)) {
            is Genres -> {
                Resource.Success(data = Genres(response.genreList))
            }
            else -> {
                Resource.DataError(errorCode = response as Int)
            }
        }
    }

    override suspend fun requestMovieDetails(movieId: String): Resource<MovieItem> {
        val movieService = serviceGenerator.createService(MovieService::class.java)
        val options = mapOf("with_genres" to movieId)
        return when (val response =
            processCall(options, responseCall = movieService::fetchMovies)) {
            is MovieItem -> {
                Resource.Success(data = response as MovieItem)
            }
            else -> {
                Resource.DataError(errorCode = response as Int)
            }
        }
    }

    override suspend fun requestMovies(genreItem: GenreItem): Resource<Movies> {
        val movieService = serviceGenerator.createService(MovieService::class.java)
        val options = mapOf("with_genres" to genreItem.id.toString())
        return when (
            val response = processCall(options, responseCall = movieService::fetchMovies)) {
            is Movies -> {
                Resource.Success(data = Movies(genreItem.name ,movies = response.movies ))
            }
            else -> {
                Resource.DataError(errorCode = response as Int)
            }
        }
    }




    private suspend fun processCall(
        options: Map<String, String>?,
        responseCall: suspend (options: Map<String, String>?) -> Response<*>
    ): Any? {
        if (!networkConnectivity.isConnected()) {
            return NO_INTERNET_CONNECTION
        }
        return try {
            val response = responseCall.invoke(options)
            val responseCode = response.code()
            if (response.isSuccessful) {
                response.body()
            } else {
                responseCode
            }
        } catch (e: IOException) {
            NETWORK_ERROR
        }
    }
}
