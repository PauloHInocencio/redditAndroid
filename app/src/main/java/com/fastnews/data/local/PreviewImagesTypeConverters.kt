package com.fastnews.data.local

import androidx.room.TypeConverter
import com.fastnews.data.model.PreviewImage
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class PreviewImagesTypeConverters {

    private var gson = Gson()

    @TypeConverter
    fun toString(list: List<PreviewImage?>?): String? {
        return gson.toJson(list)
    }

    @TypeConverter
    fun toList(string: String?): List<PreviewImage?>? {
        if (string == null) {
            return Collections.emptyList()
        }
        val previewList = object : TypeToken<List<PreviewImage>>() {}.type
        return gson.fromJson(string, previewList)
    }
}