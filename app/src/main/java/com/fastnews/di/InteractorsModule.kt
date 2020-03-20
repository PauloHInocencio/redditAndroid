package com.fastnews.di

import com.fastnews.data.repository.NewsRepository
import com.fastnews.data.repository.NewsRepositoryImpl
import com.fastnews.interactors.GetPosts
import com.fastnews.interactors.GetPostsComments
import org.koin.dsl.module

val interactorModule = module {

    single<NewsRepository> { NewsRepositoryImpl(get()) }

    single { GetPostsComments(get()) }

    single { GetPosts(get()) }


}