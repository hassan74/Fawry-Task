package com.task.data.dto.movies


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Index
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = false)
@Parcelize
@Entity(tableName = "genre",indices = [Index("id")],
        primaryKeys = ["id"])
data class GenreItem(
        @Json(name = "id")
        val id: Int  ,
        @Json(name = "name")
        val name : String  ,

) : Parcelable
