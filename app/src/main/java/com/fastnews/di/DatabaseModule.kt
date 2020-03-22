package com.fastnews.di

import androidx.room.Room
import com.fastnews.data.database.NewsDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

var databaseModule = module {

    single{
        Room.databaseBuilder(androidApplication(), NewsDatabase::class.java, "reddit.db")
            .build()
    }

    single {
        get<NewsDatabase>().postDao()
    }

}