package com.xsims.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.xsims.data.local.db.AppDatabase.Companion.DATABASE_VERSION
import com.xsims.data.models.MusicEntity

@Database(entities = [MusicEntity::class], version = DATABASE_VERSION, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

  abstract fun musicDao(): MusicDao

  companion object {
    const val DATABASE_VERSION = 1
    const val DATABASE_NAME = "app_database.db"

    fun buildInstance(context: Context): AppDatabase {
      return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
        .fallbackToDestructiveMigration()
        .build()
    }
  }
}