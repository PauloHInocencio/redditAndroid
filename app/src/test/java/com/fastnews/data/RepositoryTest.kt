package com.fastnews.data

import com.fastnews.data.repository.NewsRepository
import com.fastnews.di.interactorModule
import com.fastnews.di.networkModule
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
class RepositoryTest : KoinTest {

    private val testDispatcher = TestCoroutineDispatcher()
    private val repository : NewsRepository by inject()

    @Before
    fun setUp(){
        startKoin{
            modules(listOf(networkModule, interactorModule))
        }
        Dispatchers.setMain(testDispatcher)
    }



    @Test
    fun `getPosts() should return a not null PostResponse object`() = runBlocking {
        val response = repository.getPosts()
        Assert.assertNotNull(response)
    }

    @Test
    fun `getComments() should return a not null list of CommentResponse object`() = runBlocking {
        val response = repository.getComments("fmdcmc")
        Assert.assertNotNull(response)
    }


    @After
    fun tearDown(){
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
        stopKoin()
    }

}