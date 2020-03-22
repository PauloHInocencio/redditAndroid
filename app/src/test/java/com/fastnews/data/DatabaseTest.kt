package com.fastnews.data

import androidx.room.Room
import androidx.room.paging.LimitOffsetDataSource
import com.fastnews.data.database.NewsDatabase
import com.fastnews.data.database.PostDataDao
import com.fastnews.util.PostDataFactory
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
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

@RunWith(RobolectricTestRunner::class)
@ExperimentalCoroutinesApi
class DatabaseTest {

    private val testDispatcher = TestCoroutineDispatcher()

    private val database = Room.inMemoryDatabaseBuilder(
        RuntimeEnvironment.application.applicationContext,
        NewsDatabase::class.java)
        .allowMainThreadQueries()
        .build()


    private val postDataDao : PostDataDao = database.postDao()

    @Before
    fun setUp(){
        Dispatchers.setMain(testDispatcher)
    }


    @Test
    fun `Database should return a list with 10 PostData objects`() = runBlocking {
        postDataDao.insert(PostDataFactory.createPostDataList(10))

        val factory = postDataDao.posts()
        val postList =  (factory.create() as LimitOffsetDataSource).loadRange(0, 30)

        Assert.assertEquals(10, postList.size)
    }


    @After
    fun tearDown(){
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
        database.close()
    }

}