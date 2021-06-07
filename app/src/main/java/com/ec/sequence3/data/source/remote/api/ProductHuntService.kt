package com.ec.sequence3.data.source.remote.api

import com.ec.sequence3.data.model.PostsResponse
import retrofit2.http.GET

interface ProductHuntService {
    @GET("v1/posts?access_token=46a03e1c32ea881c8afb39e59aa17c936ff4205a8ed418f525294b2b45b56abb")
    suspend fun getPosts(): PostsResponse
}