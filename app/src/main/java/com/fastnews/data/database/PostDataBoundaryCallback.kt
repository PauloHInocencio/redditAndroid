package com.fastnews.data.database

import androidx.paging.PagedList
import com.fastnews.data.model.PostData
import com.fastnews.mechanism.Coroutines
import com.fastnews.interactors.GetPosts

class PostDataBoundaryCallback(private val db: NewsDatabase,
                               private  val getPosts: GetPosts)
    : PagedList.BoundaryCallback<PostData>() {

    override fun onZeroItemsLoaded() {
        super.onZeroItemsLoaded()
        Coroutines.io{
            db.postDao().insert(getPosts.posts())
        }
    }

    override fun onItemAtEndLoaded(itemAtEnd: PostData) {
        super.onItemAtEndLoaded(itemAtEnd)
        Coroutines.io{
            db.postDao().insert(getPosts.posts(after = itemAtEnd.name))
        }
    }

}