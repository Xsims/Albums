package com.xsims.albums.di

import com.xsims.domain.usecases.FetchMusicUseCase
import com.xsims.domain.usecases.FetchMusicsUseCase
import org.koin.dsl.module

object UseCaseModule {
  val module = module {
    factory { FetchMusicsUseCase(get()) }
    factory { FetchMusicUseCase(get()) }
  }
}