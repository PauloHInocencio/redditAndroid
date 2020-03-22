package com.fastnews.data

import com.fastnews.data.model.CommentResponse
import com.fastnews.data.model.PostResponse
import com.fastnews.data.service.NewsService
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
import retrofit2.Response

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
    fun `getPostList() should return a successful response`() = runBlocking {
        val response: Response<PostResponse>? = service.getPostList()
        Assert.assertTrue(response?.isSuccessful ?: false)
    }

    @Test
    fun `getCommentList() should return a successful response`() = runBlocking {
        val response: Response<List<CommentResponse>>? = service.getCommentList("fmdcmc")
        Assert.assertTrue(response?.isSuccessful ?: false)
    }


    @After
    fun tearDown(){
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
        stopKoin()
    }

}