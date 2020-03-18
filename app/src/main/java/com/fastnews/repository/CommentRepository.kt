package com.fastnews.repository

import com.fastnews.service.api.RedditService
import com.fastnews.service.model.CommentData

object CommentRepository : BaseRepository() {

    const val PREFIX_COMMENT = "t1"

    private val api = RedditService.createApi()

    suspend fun getComments(postId: String): List<CommentData> {

        val commentsResponse = safeApiCall(
            call = { api.getCommentList(postId).await() },
            errorMessage = "Error to fetch comments from postId -> $postId"
        )

        val result = mutableListOf<CommentData>()
        commentsResponse?.map { response ->
            response.data.children.map { data ->
                if (data.kind == PREFIX_COMMENT) {
                    result.add(data.data)
                }
            }
        }

        return result

    }

}