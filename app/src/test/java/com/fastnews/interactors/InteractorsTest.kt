package com.fastnews.interactors

import com.fastnews.di.interactorModule
import com.fastnews.di.networkModule
import com.fastnews.interactors.usecase.GetPostsCommentsUseCase
import com.fastnews.interactors.usecase.GetPostsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject

@RunWith(JUnit4::class)
@ExperimentalCoroutinesApi
class InteractorsTest : KoinTest {

    private val testDispatcher = TestCoroutineDispatcher()
    private val getPosts : GetPostsUseCase by inject()
    private val getPostsComments : GetPostsCommentsUseCase by inject()

    @Before
    fun setUp(){
        startKoin{
            modules(listOf(networkModule, interactorModule))
        }
        Dispatchers.setMain(testDispatcher)
    }



    @Test
    fun `posts() should return a list with 10 PostData objects`() = runBlocking {
        val response = getPosts.posts(limit = 10)
        Assert.assertEquals(10, response.size)
    }

    @Test
    fun `comments() should return a not empty list of CommentData object`() = runBlocking {
        val response = getPostsComments.comments("fmdcmc")
        Assert.assertTrue(response.isNotEmpty())
    }


    @After
    fun tearDown(){
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
        stopKoin()
    }

}