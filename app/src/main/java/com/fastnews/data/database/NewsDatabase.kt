package com.fastnews.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.fastnews.data.model.PostData

@Database(entities = [PostData::class ], version = 1)
@TypeConverters(PreviewImagesTypeConverters::class)
abstract class NewsDatabase : RoomDatabase() {

    companion object {
        fun createDB(context: Context): NewsDatabase {
            val dbBuilder = Room.databaseBuilder(context, NewsDatabase::class.java, "reddit.db")
            return dbBuilder.build()
        }
    }

    abstract fun postDao() : PostDataDao
}