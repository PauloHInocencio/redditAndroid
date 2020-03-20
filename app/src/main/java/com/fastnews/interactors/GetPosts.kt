package com.fastnews.interactors

import com.fastnews.data.model.PostData
import com.fastnews.data.repository.NewsRepository

class GetPosts (private val repository: NewsRepository) {
    suspend fun posts(after:String? = null,  limit: Int = 30) : List<PostData> =
        repository.getPosts(after, limit)?.data?.children?.map { it.data } ?: listOf()
}