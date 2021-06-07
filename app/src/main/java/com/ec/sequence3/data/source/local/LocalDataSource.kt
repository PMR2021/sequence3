package com.ec.sequence3.data.source.local

import android.app.Application
import androidx.room.Room
import com.ec.sequence3.data.model.Post
import com.ec.sequence3.data.source.local.database.PostDao
import com.ec.sequence3.data.source.local.database.ProductHuntRoomDatabase
import com.ec.sequence3.data.source.remote.api.ProductHuntService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LocalDataSource(
    application: Application
) {

    private val roomDatabase =
        Room.databaseBuilder(application, ProductHuntRoomDatabase::class.java, "room-database").build()


    private val postDao = roomDatabase.postDao()


    suspend fun getPosts() = postDao.getPosts()
    suspend fun saveOrUpdate(posts: List<Post>) = postDao.saveOrUpdate(posts)
}