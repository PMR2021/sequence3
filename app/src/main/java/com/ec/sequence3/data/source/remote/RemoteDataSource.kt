package com.ec.sequence3.data.source.remote

import com.ec.sequence3.data.model.Post
import com.ec.sequence3.data.source.remote.api.PostResponse
import com.ec.sequence3.data.source.remote.api.ProductHuntService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDataSource {

    private val BASE_URL = "https://api.producthunt.com/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(ProductHuntService::class.java)

    suspend fun getPosts(): List<Post> = service.getPosts().postResponses.toPosts()

    private fun List<PostResponse>.toPosts() = this.map { postResponse ->
        Post(
            id = postResponse.id,
            title = postResponse.title,
            subTitle = postResponse.subTitle,
            imageUrl = postResponse.thumbnail.imageUrl
        )

    }
}