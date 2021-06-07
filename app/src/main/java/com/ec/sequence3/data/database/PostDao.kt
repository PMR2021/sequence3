package com.ec.sequence3.data.database

import android.content.ContentValues
import android.content.Context
import com.ec.sequence3.data.model.Post

class PostDao(context: Context) {

    private val productHuntDbHelper = ProductHuntDbHelper(context)

    fun save(post: Post): Long {
        val contentValue = post.toContentValues()
        return productHuntDbHelper.writableDatabase.insert(
            DataBaseContract.PostTable.TABLE_NAME,
            null,
            contentValue
        )
    }

    fun getPosts(): List<Post> {
        val cursor = productHuntDbHelper.readableDatabase.query(
            DataBaseContract.PostTable.TABLE_NAME,
            DataBaseContract.PostTable.PROJECTIONS,
            null,
            null,
            null,
            null,
            null,
        )
        val posts = mutableListOf<Post>()
        if (cursor.moveToFirst()) {
            do {
                val post = Post(
                    id = cursor.getString(0),
                    title = cursor.getString(1),
                    subTitle = cursor.getString(2),
                    imageUrl = cursor.getString(3)
                )

                posts.add(post)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return posts
    }
}