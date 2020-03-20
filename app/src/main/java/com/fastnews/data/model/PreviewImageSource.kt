package com.fastnews.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PreviewImageSource(
    var url: String?,
    var width: Int?,
    var height: Int?
) : Parcelable