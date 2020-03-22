package com.fastnews.data.repository

import com.fastnews.data.model.CommentResponse
import com.fastnews.data.model.PostResponse
import com.fastnews.data.service.NewsService
import com.fastnews.mechanism.safeApiCall

class NewsRepositoryImpl (private val service: NewsService) : NewsRepository {

    override suspend fun getPosts(after: String?, limit: Int): PostResponse? =
        safeApiCall(
            call = { service.getPostList(after = after, limit = limit) },
            errorMessage = "Error to fetching posts"
       )

    override suspend fun getComments(postId: String): List<CommentResponse>? =
        safeApiCall(
            call = { service.getCommentList(postId) },
            errorMessage = "Error to fetch comments from postId -> $postId"
    )
}