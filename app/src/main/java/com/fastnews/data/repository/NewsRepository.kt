package com.fastnews.data.repository


import com.fastnews.data.model.CommentResponse
import com.fastnews.data.model.PostResponse

interface NewsRepository {
    suspend fun getPosts(after: String?= null, limit: Int = 30): PostResponse?
    suspend fun getComments(postId: String): List<CommentResponse>?
}