package com.task.data

import com.task.data.dto.movies.Genres
import com.task.data.dto.movies.MovieItem
import com.task.data.dto.movies.Movies
import com.task.data.local.LocalData
import com.task.data.remote.RemoteData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext


class DataRepository @Inject constructor(
    private val remoteRepository: RemoteData,
    private val localRepository: LocalData,
    private val ioDispatcher: CoroutineContext
) : DataRepositorySource {



    override suspend fun requestGenres(): Flow<Resource<Genres>> {
        return flow {
            emit(remoteRepository.requestGenres())
        }.flowOn(ioDispatcher)
    }

    override suspend fun requestMovieDetails(movieId: String): Flow<Resource<MovieItem>> {
        return flow {
            emit(remoteRepository.requestMovieDetails(movieId))
        }.flowOn(ioDispatcher)
    }


    override suspend fun requestMovies(genre: String): Flow<Resource<Movies>> {
        return flow {
            emit(remoteRepository.requestMovies(genre))
        }.flowOn(ioDispatcher)
    }

}
