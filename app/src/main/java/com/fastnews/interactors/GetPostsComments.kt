package com.fastnews.interactors

import com.fastnews.data.model.CommentData
import com.fastnews.data.repository.NewsRepository

class GetPostsComments (private val repository: NewsRepository) {

    private val PREFIX_COMMENT = "t1"

    suspend fun comments(postId: String) : List<CommentData> {
        val result = mutableListOf<CommentData>()
        repository.getComments(postId)?.map { commentResponse ->
            commentResponse.data.children.map { comment ->
                if (comment.kind == PREFIX_COMMENT) {
                    result.add(comment.data)
                }
            }
        }
        return result
    }
}