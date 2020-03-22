package com.fastnews

import com.fastnews.data.model.CommentResponse
import com.fastnews.data.model.PostResponse
import com.fastnews.data.service.NewsService
import com.fastnews.data.service.RedditService
import com.fastnews.di.networkModule
import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject

@RunWith(JUnit4::class)
@ExperimentalCoroutinesApi
class ServiceTest : KoinTest {

    private val testDispatcher = TestCoroutineDispatcher()
    private val service: NewsService by inject()


    @Before
    fun setUp(){
        startKoin{
            modules(listOf(networkModule))
        }
        Dispatchers.setMain(testDispatcher)

    }

    
    @Test
    fun `Service should return a not null PostResponse object`() = runBlocking {
        val response: PostResponse? = service.getPostList().body()
        Assert.assertNotNull(response)
    }

    @Test
    fun `Service should return a not null CommentResponse list`() = runBlocking {
        val response: List<CommentResponse>? = service.getCommentList("fmdcmc").body()
        Assert.assertNotNull(response)
    }




    @After
    fun tearDown(){
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
        stopKoin()
    }



}