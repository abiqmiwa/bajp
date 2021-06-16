package com.dicoding.picodiploma.bajp2

import android.content.Context
import com.dicoding.picodiploma.bajp2.data.Repository
import com.dicoding.picodiploma.bajp2.data.source.local.LocalDS
import com.dicoding.picodiploma.bajp2.data.source.local.room.DataDatabase
import com.dicoding.picodiploma.bajp2.data.source.remote.RemoteDataSource
import com.dicoding.picodiploma.bajp2.etc.AppExecutors

object Injection {
    fun provideRepository(context: Context): Repository {
        val remoteDataSource = RemoteDataSource.getInstance()
        val database = DataDatabase.getInstance(context)
        val localDS = LocalDS.getInstance(database.dataDao())
        val appExecutors = AppExecutors()
        return Repository.getInstance(remoteDataSource, localDS, appExecutors)
    }
}