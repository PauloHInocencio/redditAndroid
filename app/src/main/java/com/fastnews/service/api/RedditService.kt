package com.fastnews.service.api

import com.fastnews.BuildConfig
import com.fastnews.service.model.CommentResponse
import com.fastnews.service.model.PostResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import okhttp3.logging.HttpLoggingInterceptor.Level.NONE
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RedditService {

    @GET("new/.json?")
    fun getPostList(
        @Query("after") after: String? = null,
        @Query("before") before:String? = null,
        @Query("limit") limit: Int = 6): Deferred<Response<PostResponse>>

    @GET("comments/{id}.json")
    fun getCommentList(@Path("id") postId: String): Deferred<Response<List<CommentResponse>>>


    companion object {
        fun createApi() : RedditService {
            val interceptor = HttpLoggingInterceptor().apply { if (BuildConfig.DEBUG) BODY else NONE }
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
            return Retrofit.Builder()
                .baseUrl(BuildConfig.API_ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .client(client)
                .build()
                .create(RedditService::class.java)
        }
    }
}