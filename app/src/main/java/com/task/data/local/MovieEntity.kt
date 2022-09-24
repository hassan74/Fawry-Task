package com.task.data.local


import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.task.data.dto.movies.GenreItem
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = false)
@Parcelize
@Entity(
    tableName = "movie", indices = [Index("id")],
    primaryKeys = ["id", "genre_id"], foreignKeys = [ForeignKey(
        entity = GenreItem::class,
        parentColumns = ["id"],
        childColumns = ["genre_id"],
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE
    )]
)
data class MovieEntity(
    @Json(name = "id")
    val id: Int,
    @ColumnInfo(name = "genre_id")
    var genreId: Int,
    @Json(name = "title")
    val name: String,
    @ColumnInfo(name = "poster_path")
    @Json(name = "poster_path")
    val posterPath: String,
    @Json(name = "overview")
    val overview: String,
    @Json(name = "vote_average")
    val vote: Float,

    ) : Parcelable
