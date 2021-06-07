package com.ec.sequence3.data

import android.app.Application
import androidx.room.Room
import com.ec.sequence3.data.source.remote.api.ProductHuntService
import com.ec.sequence3.data.source.local.database.ProductHuntRoomDatabase
import com.ec.sequence3.data.model.Post
import com.ec.sequence3.data.source.local.LocalDataSource
import com.ec.sequence3.data.source.remote.RemoteDataSource
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

class PostRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) {

    suspend fun getPost(): List<Post> {
        return try {

            remoteDataSource.getPosts().also {
                localDataSource.saveOrUpdate(it)
            }

        } catch (e: Exception) {
            localDataSource.getPosts()
        }
    }


    companion object {
        fun newInstance(application: Application): PostRepository {
            return PostRepository(
                localDataSource = LocalDataSource(application),
                remoteDataSource = RemoteDataSource()
            )
        }
    }


}