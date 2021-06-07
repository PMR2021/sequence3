package com.ec.sequence3.data.api

import com.google.gson.annotations.SerializedName

data class PostResponse(
    @SerializedName("id")
    val id : String,
    @SerializedName("name")
    val title: String,
    @SerializedName("tagline")
    val subTitle: String,
    @SerializedName("thumbnail")
    val thumbnail : Thumbnail

)

data class Thumbnail(
    @SerializedName("image_url")
    val imageUrl : String
)