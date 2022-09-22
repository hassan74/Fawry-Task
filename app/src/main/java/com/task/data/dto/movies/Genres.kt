package com.task.data.dto.movies

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = false)
@Parcelize
data class Genres(
    @Json(name = "genres")
    val genreList: List<GenreItem>
): Parcelable
