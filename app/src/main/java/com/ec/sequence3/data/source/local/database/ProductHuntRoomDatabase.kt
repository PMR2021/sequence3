package com.ec.sequence3.data.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ec.sequence3.data.model.Post

@Database(
    entities = [
        Post::class
    ],
    version = 1
)
abstract class ProductHuntRoomDatabase : RoomDatabase() {

    abstract fun postDao(): PostDao
}