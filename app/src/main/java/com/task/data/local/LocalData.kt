package com.task.data.local

import android.content.Context
import android.content.SharedPreferences
import com.task.CACHED_DATE_KEY
import com.task.SHARED_PREFERENCES_NAME
import com.task.data.Resource
import com.task.data.dto.movies.GenreItem
import com.task.data.dto.movies.Genres
import com.task.data.dto.movies.Movies
import com.task.utils.toMovieEntity
import com.task.utils.toMovieItem
import javax.inject.Inject


class LocalData @Inject constructor(
    private val movieDao: MovieDao,
    private val genreDao: GenreDao,
    val context: Context
) {
    suspend fun getCachedGenres(): Resource<Genres> {
        return Resource.Success(Genres(genreDao.getGenres()))
    }

    suspend fun getCachedMovies(genreItem: GenreItem): Resource<Movies> {
        val movies = movieDao.queryMovieByGenre(genreItem.id.toString())
        return Resource.Success(Movies(genreItem.name, movies.toMovieItem()))
    }

    suspend fun cacheGenres(genres: Genres): Resource<Boolean> {
        genreDao.insert(genres.genreList)
        return Resource.Success(true)
    }

    fun deleteCache() {
        movieDao.deleteAll()
        genreDao.deleteAll()
    }

    suspend fun cacheMovies(movies: Movies): Resource<Boolean> {
        movieDao.insert(movies.movies.toMovieEntity())
        return Resource.Success(true)
    }

    fun getLastCachedDate(): Resource<Long> {
        val sharedPref = context.getSharedPreferences(SHARED_PREFERENCES_NAME, 0)
        val cachedKey = sharedPref.getLong(CACHED_DATE_KEY, 0)
        return Resource.Success(cachedKey)
    }

    fun cacheLastDate(date: Long): Resource<Boolean> {
        val sharedPref = context.getSharedPreferences(SHARED_PREFERENCES_NAME, 0)
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putLong(CACHED_DATE_KEY, date)
        editor.apply()
        val isSuccess = editor.commit()
        return Resource.Success(isSuccess)
    }

}
