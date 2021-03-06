package com.ec.sequence3.data.model

import com.ec.sequence3.data.source.remote.api.PostResponse
import com.google.gson.annotations.SerializedName

data class PostsResponse(
    @SerializedName("posts")
    val postResponses : List<PostResponse>)