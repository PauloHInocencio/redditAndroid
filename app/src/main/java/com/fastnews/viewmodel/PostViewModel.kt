package com.fastnews.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.fastnews.data.local.RedditDatabase
import com.fastnews.data.model.PostData
import com.fastnews.data.local.PostDataBoundaryCallback


class PostViewModel(application: Application) : AndroidViewModel(application) {

     fun getPosts(limit: Int = 50) : LiveData<PagedList<PostData>> {
         val config = PagedList.Config.Builder()
            .setPageSize(limit)
            .setEnablePlaceholders(true)
            .build()

         val database = RedditDatabase.createDB(this.getApplication())

         val builder = LivePagedListBuilder<Int, PostData>(database.postDao().posts(), config)
             .setBoundaryCallback(
                 PostDataBoundaryCallback(
                     database
                 )
             )

        return builder.build()
    }

}