package com.xsims.albums.di

import com.xsims.data.repositories.MusicRepositoryImpl
import com.xsims.domain.repositories.MusicRepository
import org.koin.dsl.module

object RepositoryModule {
  val module = module {
    single<MusicRepository> { MusicRepositoryImpl(get(), get()) }
  }
}