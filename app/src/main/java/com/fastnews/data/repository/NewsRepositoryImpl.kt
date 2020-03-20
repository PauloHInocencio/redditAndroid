package com.fastnews.data.repository

import com.fastnews.data.model.CommentResponse
import com.fastnews.data.model.PostResponse
import com.fastnews.data.service.RedditService
import com.fastnews.mechanism.safeApiCall

class NewsRepositoryImpl (val api: RedditService) : NewsRepository {

    override suspend fun getPosts(after: String?, limit: Int): PostResponse? =
        safeApiCall(
            call = { api.getPostList(after = after, limit = limit).await() },
            errorMessage = "Error to fetching posts"
       )

    override suspend fun getComments(postId: String): List<CommentResponse>? =
        safeApiCall(
            call = { api.getCommentList(postId).await() },
            errorMessage = "Error to fetch comments from postId -> $postId"
    )
}