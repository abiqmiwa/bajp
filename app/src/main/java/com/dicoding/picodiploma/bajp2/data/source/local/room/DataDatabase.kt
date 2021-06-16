package com.dicoding.picodiploma.bajp2.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dicoding.picodiploma.bajp2.data.source.local.entity.MovieEntity
import com.dicoding.picodiploma.bajp2.data.source.local.entity.TvShowEntity

@Database(entities = [MovieEntity::class, TvShowEntity::class], version = 1, exportSchema = false)
abstract class DataDatabase : RoomDatabase() {
    abstract fun dataDao(): DataDao

    companion object {
        @Volatile
        private var INSTANCE: DataDatabase? = null

        fun getInstance(context: Context): DataDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: Room.databaseBuilder(context.applicationContext, DataDatabase::class.java, "Data.db").build()
            }
    }
}