package com.task.data.local

import androidx.room.*
import com.task.data.dto.movies.GenreItem
import kotlinx.coroutines.flow.Flow

@Dao
interface GenreDao {

    //@Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(topics: List<GenreItem>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(topic: GenreItem): Long

    @Query("SELECT genre.* FROM `genre` order by name ASC")
    suspend fun getGenres(): List<GenreItem>


    @Delete
    fun delete(genreItem: GenreItem)

    @Query("DELETE FROM `genre`")
    fun deleteAll()

}