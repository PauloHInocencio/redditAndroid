package com.fastnews.source

import androidx.paging.PageKeyedDataSource
import com.fastnews.mechanism.Coroutines
import com.fastnews.repository.PostRepository
import com.fastnews.service.model.PostData

class PostDataSource : PageKeyedDataSource<String, PostData>() {

    override fun loadInitial(params: LoadInitialParams<String>, callback: LoadInitialCallback<String, PostData>) {
        Coroutines.io{
            val data = PostRepository.getPosts(limit= params.requestedLoadSize)?.data
            val posts= data?.children?.map { it.data }
            callback.onResult(posts ?: listOf(), data?.before, data?.after)
        }
    }

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, PostData>) {
        Coroutines.io{
            val data =  PostRepository.getPosts(after = params.key, limit= params.requestedLoadSize)?.data
            val posts = data?.children?.map { it.data }
            callback.onResult(posts ?: listOf(), data?.after)
        }

    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<String, PostData>) {
        Coroutines.io{
            val data = PostRepository.getPosts(before = params.key,  limit= params.requestedLoadSize)?.data
            val posts = data?.children?.map { it.data }
            callback.onResult(posts ?: listOf(), data?.before)
        }
    }

}