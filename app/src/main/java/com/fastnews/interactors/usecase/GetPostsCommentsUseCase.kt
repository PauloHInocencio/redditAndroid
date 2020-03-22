package com.fastnews.interactors.usecase

import com.fastnews.data.model.CommentData

interface GetPostsCommentsUseCase {
    suspend fun comments(postId: String) : List<CommentData>
}