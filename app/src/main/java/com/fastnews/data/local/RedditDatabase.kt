package com.fastnews.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.fastnews.data.model.PostData
import com.fastnews.data.model.Preview
import com.fastnews.data.model.PreviewImage
import com.fastnews.data.model.PreviewImageSource

@Database(entities = [PostData::class ], version = 1)
@TypeConverters(PreviewImagesTypeConverters::class)
abstract class RedditDatabase : RoomDatabase() {

    companion object {
        fun createDB(context: Context): RedditDatabase {
            val dbBuilder = Room.databaseBuilder(context, RedditDatabase::class.java, "reddit.db")
            return dbBuilder.build()
        }
    }

    abstract fun postDao() : PostDataDao
}