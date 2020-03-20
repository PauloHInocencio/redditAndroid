package com.fastnews.viewmodel

import androidx.annotation.UiThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.fastnews.data.database.NewsDatabase
import com.fastnews.data.model.PostData
import com.fastnews.data.database.PostDataBoundaryCallback
import com.fastnews.interactors.GetPosts

class PostViewModel(
    private val database: NewsDatabase,
    private val getPosts: GetPosts
    ) : ViewModel(){

    private lateinit var liveData: LiveData<PagedList<PostData>>

     @UiThread
     fun getPosts(limit: Int = 50) : LiveData<PagedList<PostData>> {
         if(!::liveData.isInitialized) {
             val config = PagedList.Config.Builder()
                 .setPageSize(limit)
                 .setEnablePlaceholders(true)
                 .build()
             val builder = LivePagedListBuilder<Int, PostData>(database.postDao().posts(), config)
                 .setBoundaryCallback(PostDataBoundaryCallback(database, getPosts))
            liveData = builder.build()
         }

        return liveData
    }

}