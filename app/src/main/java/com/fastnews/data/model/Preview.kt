package com.fastnews.data.model

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.fastnews.data.local.PreviewImagesTypeConverters
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Preview(
    var previewId:Int?,
    var images: List<PreviewImage>?
): Parcelable