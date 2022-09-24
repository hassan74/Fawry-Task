package com.task.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(topics: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(topic: MovieEntity): Long

    @Query("SELECT movie.* FROM `movie`")
    suspend fun queryMovies(): List<MovieEntity>


    @Query("SELECT movie.* FROM `movie` WHERE `id` = :movieId")
    fun queryMovie(movieId: String): Flow<List<MovieEntity>>

    @Query("SELECT movie.* FROM `movie` WHERE `genre_id` = :genreId order by name ASC")
    suspend fun queryMovieByGenre(genreId: String): List<MovieEntity>

    @Delete
    fun delete(movieItem: MovieEntity)

    @Query("DELETE FROM `movie`")
    fun deleteAll()

}