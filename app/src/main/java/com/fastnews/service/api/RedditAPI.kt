package com.fastnews.service.api

import com.fastnews.BuildConfig
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RedditAPI {


    var builder: Retrofit.Builder = Retrofit.Builder()
        .baseUrl(BuildConfig.API_ENDPOINT)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())

    var loggingInterceptor = HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
    }

    var httpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)

    var retrofit = builder
        .client(httpClient.build())
        .build()

    var redditService = retrofit.create<RedditService>(
        RedditService::class.java)

}