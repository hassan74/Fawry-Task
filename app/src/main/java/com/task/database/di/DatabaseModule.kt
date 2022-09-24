/*
 * Copyright (C) 2022, Kasem S.M
 * All rights reserved.
 */
package com.task.database.di

import android.content.Context
import androidx.room.Room
import com.task.database.db.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module()
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    internal fun provideMovieDao(db: MovieDatabase) = db.movieDao()

    @Provides
    @Singleton
    internal fun provideGenreDao(db: MovieDatabase) = db.genreDao()

    @Provides
    @Singleton
    internal fun provideMovieDatabase(@ApplicationContext context: Context): MovieDatabase {
        return Room.databaseBuilder(
            context,
            MovieDatabase::class.java,
            "movie_db"
        ).fallbackToDestructiveMigration().build()
    }
}
