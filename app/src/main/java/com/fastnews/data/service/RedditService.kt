package com.fastnews.data.service

import com.fastnews.data.model.CommentResponse
import com.fastnews.data.model.PostResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RedditService {

    @GET("new/.json?")
    fun getPostList(
        @Query("after") after: String? = null,
        @Query("limit") limit: Int = 30): Deferred<Response<PostResponse>>

    @GET("comments/{id}.json")
    fun getCommentList(@Path("id") postId: String): Deferred<Response<List<CommentResponse>>>

}