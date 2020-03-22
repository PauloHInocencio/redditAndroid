package com.fastnews.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.fastnews.data.model.PostData

@Database(entities = [PostData::class ], version = 1)
@TypeConverters(PreviewImagesTypeConverters::class)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun postDao() : PostDataDao
}