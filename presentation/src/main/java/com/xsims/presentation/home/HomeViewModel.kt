package com.xsims.presentation.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.xsims.domain.usecases.FetchMusicsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(
  private val fetchMusicsUseCase: FetchMusicsUseCase
) : ViewModel() {

  var musics = fetchMusicsUseCase.invoke().asLiveData() as MutableLiveData

  fun reloadMusics() {
    viewModelScope.launch(Dispatchers.IO) {
      fetchMusicsUseCase.invoke().collect {
        musics.postValue(it)
      }
    }
  }
}