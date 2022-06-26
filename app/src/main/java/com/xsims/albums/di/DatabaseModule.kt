package com.xsims.albums.di

import com.xsims.data.local.db.AppDatabase
import com.xsims.data.local.db.MusicDatabaseSource
import com.xsims.data.local.db.MusicDatabaseSourceImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

object DatabaseModule {
  val module = module {

    single {
      AppDatabase.buildInstance(androidContext())
    }

    single {
      get<AppDatabase>().musicDao()
    }

    single<MusicDatabaseSource> {
      MusicDatabaseSourceImpl(get())
    }
  }
}