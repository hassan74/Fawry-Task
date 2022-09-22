package com.task.data.dto.movies

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = false)
@Parcelize
data class Movies(
    var genre :String? =null ,
    @Json(name = "results")
    val movies: List<MovieItem>
): Parcelable

