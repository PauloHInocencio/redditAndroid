package com.fastnews.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Preview(
    var previewId:Int?,
    var images: List<PreviewImage>?
): Parcelable