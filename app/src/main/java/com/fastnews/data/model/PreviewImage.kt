package com.fastnews.data.model

import android.os.Parcelable
import androidx.room.*
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PreviewImage(
    @PrimaryKey
    @ColumnInfo(name = "previewImageId")
    var id: String,
    @Embedded var source: PreviewImageSource?
) : Parcelable