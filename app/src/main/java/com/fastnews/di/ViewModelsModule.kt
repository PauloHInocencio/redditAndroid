package com.fastnews.di

import com.fastnews.data.database.NewsDatabase
import com.fastnews.viewmodel.CommentViewModel
import com.fastnews.viewmodel.PostViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    single{ NewsDatabase.createDB(get()) }

    viewModel{ CommentViewModel(get()) }

    viewModel { PostViewModel(get(), get()) }

}