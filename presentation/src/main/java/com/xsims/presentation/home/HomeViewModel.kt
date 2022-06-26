package com.xsims.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.xsims.domain.usecases.FetchMusicsUseCase

class HomeViewModel(
  fetchMusicsUseCase: FetchMusicsUseCase
) : ViewModel() {

  val musics = fetchMusicsUseCase.invoke().map {
    it.toString()
  }
}