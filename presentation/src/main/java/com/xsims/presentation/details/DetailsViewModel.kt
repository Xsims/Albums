package com.xsims.presentation.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xsims.common.models.UiState.Success
import com.xsims.domain.usecases.FetchMusicUseCase
import com.xsims.presentation.models.MusicUi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsViewModel(
  private val fetchMusicUseCase: FetchMusicUseCase
) : ViewModel() {

  val music = MutableLiveData<MusicUi>()

  fun getMusic(musicId: Int) {
    viewModelScope.launch(Dispatchers.IO) {
      val state = fetchMusicUseCase.getMusic(musicId).mapData { MusicUi.mapFrom(it) as MusicUi }
      if (state is Success)
        music.postValue(state.data)
    }
  }
}