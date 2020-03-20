package com.fastnews.data.model


import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "posts")
data class PostData(
    @PrimaryKey
    var id: String,
    var author: String?,
    var thumbnail: String?,
    var name: String?,
    var num_comments:Int?,
    var score: Int?,
    var title: String?,
    var created_utc: Long?,
    var url: String?,
    @Embedded var preview: Preview?
) : Parcelable