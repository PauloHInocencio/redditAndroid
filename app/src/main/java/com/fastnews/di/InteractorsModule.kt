package com.fastnews.di

import com.fastnews.data.repository.NewsRepository
import com.fastnews.data.repository.NewsRepositoryImpl
import com.fastnews.interactors.GetPosts
import com.fastnews.interactors.GetPostsComments
import com.fastnews.interactors.usecase.GetPostsCommentsUseCase
import com.fastnews.interactors.usecase.GetPostsUseCase
import org.koin.dsl.module

val interactorModule = module {

    single<NewsRepository> { NewsRepositoryImpl(get()) }

    single<GetPostsCommentsUseCase> { GetPostsComments(get()) }

    single<GetPostsUseCase> { GetPosts(get()) }


}