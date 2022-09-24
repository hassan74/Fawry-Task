package com.task.data

import com.task.data.dto.movies.Genres
import com.task.data.dto.movies.MovieItem
import com.task.data.dto.movies.Movies
import com.task.data.local.LocalData
import com.task.data.remote.RemoteData
import com.task.utils.fourHourLonger
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.util.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext


class DataRepository @Inject constructor(
    private val remoteData: RemoteData,
    private val localData: LocalData,
    private val ioDispatcher: CoroutineContext
) : DataRepositorySource {


    override suspend fun requestGenres(): Flow<Resource<Genres>> {
        return when (isCached()) {
            true -> getCachedGenres()
            false -> getRemoteGenres()
        }
    }

    override suspend fun requestMovies(genres: Genres): Flow<Resource<Movies>> {
        return when (isCached()) {
            true -> getCachedMovies(genres)
            false -> getRemoteMovies(genres)
        }
    }

    private suspend fun getCachedGenres(): Flow<Resource<Genres>> {
        return flow {
            val result = localData.getCachedGenres()
            emit(result)
        }.flowOn(ioDispatcher)
    }

    private suspend fun getRemoteGenres(): Flow<Resource<Genres>> {
        return flow {
            val result = remoteData.requestGenres()
            localData.deleteCache()
            if (result is Resource.Success)
                cacheGenres(result.data)
            emit(result)
        }.flowOn(ioDispatcher)
    }


    private suspend fun getCachedMovies(genres: Genres): Flow<Resource<Movies>> {
        return flow {
            genres.genreList.map { genre ->
                val result = localData.getCachedMovies(genre)
                //cache movies
                emit(result)
            }
            localData.cacheLastDate(Calendar.getInstance().time.time)
        }.flowOn(ioDispatcher)

    }

    private suspend fun getRemoteMovies(genres: Genres): Flow<Resource<Movies>> {
        return flow {
            genres.genreList.map { genre ->
                val result = remoteData.requestMovies(genre)
                //cache movies
                if (result is Resource.Success) {
                    cacheMovies(result.data?.movies, genre.id)
                }
                emit(result)
            }
            localData.cacheLastDate(Calendar.getInstance().time.time)

        }.flowOn(ioDispatcher)
    }

    private suspend fun cacheGenres(genres: Genres?) {
        genres?.let { it ->
            localData.cacheGenres(it)
        }
    }

    private suspend fun cacheMovies(movies: List<MovieItem>?, genreId: Int) {
        //add genre id to each movie item
        movies?.let { it ->
            val items = it.map { movie ->
                movie.genreId = genreId
                movie
            }
            localData.cacheMovies(Movies(movies = items))
        }
    }

    override suspend fun requestMovieDetails(movieId: String): Flow<Resource<MovieItem>> {
        return flow {
            emit(remoteData.requestMovieDetails(movieId))
        }.flowOn(ioDispatcher)
    }

    private fun isCached(): Boolean {
        if (fourHourLonger(localData.getLastCachedDate().data ?: 0))
            return false
        return true
    }

}
