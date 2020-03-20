package com.fastnews.repository

import com.fastnews.data.remote.RedditService
import com.fastnews.data.model.PostResponse

object PostRepository : BaseRepository() {

    private val api = RedditService.createApi()

    suspend fun getPosts(after: String?= null, before:String? = null, limit: Int = 30): PostResponse? =
        safeApiCall(
            call = { api.getPostList(after = after, before = before, limit = limit).await() },
            errorMessage = "Error to fetching posts"
        )
}