package com.fastnews.data.local

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fastnews.data.model.PostData

@Dao
interface PostDataDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(posts: List<PostData>)

    @Query("SELECT * FROM posts")
    fun posts(): DataSource.Factory<Int, PostData>
}

