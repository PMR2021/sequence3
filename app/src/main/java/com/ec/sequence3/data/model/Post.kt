package com.ec.sequence3.data.model

import android.content.ContentValues
import com.ec.sequence3.data.database.DataBaseContract
import com.google.gson.annotations.SerializedName

data class Post(
    val id: String,
    val title: String,
    val subTitle: String,
    val imageUrl: String,
) {
    fun toContentValues(): ContentValues {
        val contentValues = ContentValues()
        contentValues.put(DataBaseContract.PostTable.ID_COLUMN, this.id)
        contentValues.put(DataBaseContract.PostTable.TITLE_COLUMN, this.title)
        contentValues.put(DataBaseContract.PostTable.SUBTITLE_COLUMN, this.subTitle)
        contentValues.put(DataBaseContract.PostTable.IMAGE_URL_COLUMN, this.imageUrl)

        return contentValues

    }
}

