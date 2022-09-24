/*
 * Copyright (C) 2022, Kasem S.M
 * All rights reserved.
 */
package com.task.database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.task.data.dto.movies.GenreItem
import com.task.data.dto.movies.MovieItem
import com.task.data.local.GenreDao
import com.task.data.local.MovieDao
import com.task.data.local.MovieEntity

@Database(
    entities = [
        MovieEntity::class,
        GenreItem::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    abstract fun genreDao(): GenreDao
}
