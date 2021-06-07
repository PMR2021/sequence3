package com.ec.sequence3.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ec.sequence3.data.model.Post

@Dao
interface PostDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveOrUpdate(posts: List<Post>)

    @Query("SELECT * FROM POST")
    suspend fun getPosts(): List<Post>
}