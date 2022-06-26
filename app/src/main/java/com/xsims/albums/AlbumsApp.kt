package com.xsims.albums

import android.app.Application
import com.xsims.albums.di.DatabaseModule
import com.xsims.albums.di.RemoteModule
import com.xsims.albums.di.RepositoryModule
import com.xsims.albums.di.UseCaseModule
import com.xsims.albums.di.ViewModelModule
import com.xsims.data.local.db.AppDatabase
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AlbumsApp : Application() {

  override fun onCreate() {
    super.onCreate()

    startKoin {
      androidContext(this@AlbumsApp)
      modules(
        DatabaseModule.module,
        RemoteModule.module,
        RepositoryModule.module,
        UseCaseModule.module,
        ViewModelModule.module,
      )
      getKoin().get<AppDatabase>()
      getKoin().get<AppDatabase>().musicDao()
    }
  }
}