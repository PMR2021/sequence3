package com.ec.sequence3.data

import android.content.Context
import androidx.room.Room
import com.ec.sequence3.data.api.PostResponse
import com.ec.sequence3.data.api.ProductHuntService
import com.ec.sequence3.data.database.ProductHuntRoomDatabase
import com.ec.sequence3.data.model.Post
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

class DataProvider(context: Context) {


    private val BASE_URL = "https://api.producthunt.com/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val roomDatabase =
        Room.databaseBuilder(context, ProductHuntRoomDatabase::class.java, "room-database").build()

    private val service = retrofit.create(ProductHuntService::class.java)
    private val postDao = roomDatabase.postDao()


    suspend fun getPost(): List<Post> {
       return   try {

           service.getPosts().postResponses.toPosts().also {
               postDao.saveOrUpdate(it)
           }

        } catch (e: Exception) {
            postDao.getPosts()
        }
    }

    private fun List<PostResponse>.toPosts() = this.map { postResponse ->
        Post(
            id = postResponse.id,
            title = postResponse.title,
            subTitle = postResponse.subTitle,
            imageUrl = postResponse.thumbnail.imageUrl
        )

    }
}