package com.fastnews.data.local

import androidx.paging.PagedList
import com.fastnews.data.model.PostData
import com.fastnews.mechanism.Coroutines
import com.fastnews.repository.PostRepository

class PostDataBoundaryCallback(private val db: RedditDatabase) : PagedList.BoundaryCallback<PostData>() {

    override fun onZeroItemsLoaded() {
        super.onZeroItemsLoaded()
        Coroutines.io{
            val posts = PostRepository.getPosts()?.data?.children?.map { it.data }
            db.postDao().insert(posts ?: listOf())
        }
    }

    override fun onItemAtEndLoaded(itemAtEnd: PostData) {
        super.onItemAtEndLoaded(itemAtEnd)
        Coroutines.io{
            val posts =  PostRepository.getPosts(after = itemAtEnd.name)?.data?.children?.map { it.data }
            db.postDao().insert(posts ?: listOf())
        }
    }

}