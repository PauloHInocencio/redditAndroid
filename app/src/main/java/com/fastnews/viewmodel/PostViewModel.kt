package com.fastnews.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.fastnews.data.model.PostData
import com.fastnews.data.database.PostDataBoundaryCallback
import com.fastnews.data.database.PostDataDao
import com.fastnews.interactors.usecase.GetPostsUseCase

class PostViewModel(
    private val postDao: PostDataDao,
    private val getPosts: GetPostsUseCase
    ) : ViewModel(){

    private lateinit var liveData: LiveData<PagedList<PostData>>

     fun getPosts(limit: Int = 50) : LiveData<PagedList<PostData>> {
         if(!::liveData.isInitialized) {

             val config = PagedList.Config.Builder()
                 .setPageSize(limit)
                 .setEnablePlaceholders(true)
                 .build()

             val builder = LivePagedListBuilder<Int, PostData>(postDao.posts(), config)
                 .setBoundaryCallback(PostDataBoundaryCallback(postDao, getPosts))

            liveData = builder.build()
         }

        return liveData
    }

}