package com.xsims.albums.di

import com.skydoves.sandwich.coroutines.CoroutinesResponseCallAdapterFactory
import com.xsims.common.BuildConfig
import com.xsims.data.remote.api.MusicApi
import com.xsims.data.remote.api.MusicService
import com.xsims.data.remote.api.MusicServiceImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BASIC
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RemoteModule {
  val module = module {

    single {
      OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(if (BuildConfig.DEBUG) BODY else BASIC))
        .build()
    }

    single<Retrofit> {
      Retrofit.Builder()
        .client(get())
        .baseUrl(MusicApi.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutinesResponseCallAdapterFactory.create())
        .build()
    }

    single<MusicApi> {
      get<Retrofit>().create(MusicApi::class.java)
    }

    single<MusicService> {
      MusicServiceImpl(get())
    }
  }
}
