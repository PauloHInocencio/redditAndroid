package com.fastnews.data.database

import androidx.paging.PagedList
import com.fastnews.data.model.PostData
import com.fastnews.mechanism.Coroutines
import com.fastnews.interactors.usecase.GetPostsUseCase

class PostDataBoundaryCallback(private val postDao: PostDataDao,
                               private  val getPosts: GetPostsUseCase)
    : PagedList.BoundaryCallback<PostData>() {

    override fun onZeroItemsLoaded() {
        super.onZeroItemsLoaded()
        Coroutines.io{
            postDao.insert(getPosts.posts())
        }
    }

    override fun onItemAtEndLoaded(itemAtEnd: PostData) {
        super.onItemAtEndLoaded(itemAtEnd)
        Coroutines.io{
            postDao.insert(getPosts.posts(after = itemAtEnd.name))
        }
    }

}