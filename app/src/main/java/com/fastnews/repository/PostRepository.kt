package com.fastnews.repository

import com.fastnews.service.api.RedditService
import com.fastnews.service.model.PostResponse

object PostRepository : BaseRepository() {

    private val api = RedditService.createApi()

    suspend fun getPosts(after: String?= null, before:String? = null, limit: Int): PostResponse? =
        safeApiCall(
            call = { api.getPostList(after = after, before = before, limit = limit).await() },
            errorMessage = "Error to fetching posts"
        )
}