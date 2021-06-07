package com.ec.sequence3.data

import android.content.Context
import com.ec.sequence3.data.api.PostResponse
import com.ec.sequence3.data.api.ProductHuntService
import com.ec.sequence3.data.database.PostDao
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


    private val service = retrofit.create(ProductHuntService::class.java)
    private val dao = PostDao(context)


    suspend fun getPost(): List<Post> {
        return try {
            val posts = service.getPosts().postResponses.toPosts()

            posts.forEach {

                dao.save(it)
            }

            posts

        } catch (e: Exception) {
            dao.getPosts()
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