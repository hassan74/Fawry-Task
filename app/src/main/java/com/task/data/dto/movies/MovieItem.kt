package com.task.data.dto.movies


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = false)
@Parcelize
data class MovieItem(
        @Json(name = "id")
        val id: Int  ,
        @Json(name = "title")
        val name : String  ,
        @Json(name = "poster_path")
        val posterPath : String  ,

) : Parcelable
