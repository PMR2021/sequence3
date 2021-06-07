package com.ec.sequence3.data.model

import android.content.ContentValues
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Post(
    @PrimaryKey
    val id: String,
    val title: String,
    val subTitle: String,
    val imageUrl: String,
)
