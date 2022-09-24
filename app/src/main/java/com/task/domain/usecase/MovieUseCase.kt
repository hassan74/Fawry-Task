package com.task.domain.usecase

import com.task.data.DataRepository
import com.task.data.Resource
import com.task.data.dto.movies.GenreItem
import com.task.data.dto.movies.Genres
import com.task.data.dto.movies.Movies
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class MovieUseCase @Inject constructor(
    private val repository: DataRepository
) {
    suspend operator fun invoke(genres: Genres): Flow<Resource<Movies>> =
        repository.requestMovies(genres)
}