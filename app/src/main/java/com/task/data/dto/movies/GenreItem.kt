package com.task.data.dto.movies


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = false)
@Parcelize
data class GenreItem(
        @Json(name = "id")
        val id: Int  ,
        @Json(name = "name")
        val name : String  ,

) : Parcelable
