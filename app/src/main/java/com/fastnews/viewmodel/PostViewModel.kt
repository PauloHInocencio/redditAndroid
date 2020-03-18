package com.fastnews.viewmodel

import android.app.Application
import androidx.annotation.UiThread
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.fastnews.service.model.PostData
import com.fastnews.source.PostDataSource


class PostViewModel(application: Application) : AndroidViewModel(application) {

    @UiThread
     fun getPosts(limit: Int = 50) : LiveData<PagedList<PostData>> {

        val config = PagedList.Config.Builder()
            .setPageSize(limit)
            .setEnablePlaceholders(true)
            .build()

        val dataSourceFactory = object : DataSource.Factory<String, PostData>() {
            override fun create(): DataSource<String, PostData> {
                return PostDataSource()
            }
        }

        return LivePagedListBuilder<String, PostData>(dataSourceFactory, config).build()

    }

}