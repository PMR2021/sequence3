package com.ec.sequence3.data

import com.ec.sequence3.data.api.ProductHuntService
import com.ec.sequence3.data.model.Post
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DataProvider {


    private val BASE_URL = "https://api.producthunt.com/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    private val service = retrofit.create(ProductHuntService::class.java)


    suspend fun getPostFromApi(): List<Post> {
        return service.getPosts().posts
    }



}