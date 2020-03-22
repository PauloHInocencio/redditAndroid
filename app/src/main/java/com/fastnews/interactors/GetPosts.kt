package com.fastnews.interactors

import com.fastnews.data.model.PostData
import com.fastnews.data.repository.NewsRepository
import com.fastnews.interactors.usecase.GetPostsUseCase

class GetPosts (private val repository: NewsRepository) : GetPostsUseCase {
    override suspend fun posts(after: String?, limit: Int): List<PostData> =
        repository.getPosts(after, limit)?.data?.children?.map { it.data } ?: listOf()
}