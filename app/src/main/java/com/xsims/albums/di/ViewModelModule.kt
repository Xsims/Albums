package com.xsims.albums.di

import com.xsims.presentation.details.DetailsViewModel
import com.xsims.presentation.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object ViewModelModule {
  val module = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailsViewModel(get()) }
  }
}