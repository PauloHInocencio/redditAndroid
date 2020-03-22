package com.fastnews.interactors.usecase

import com.fastnews.data.model.PostData

interface GetPostsUseCase {
    suspend fun posts(after:String? = null,  limit: Int = 30) : List<PostData>
}