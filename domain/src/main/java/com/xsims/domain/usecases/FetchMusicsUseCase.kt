package com.xsims.domain.usecases

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.xsims.common.models.UiState
import com.xsims.domain.models.Music
import com.xsims.domain.repositories.MusicRepository

class FetchMusicsUseCase(
  private val musicRepository: MusicRepository
) {
  operator fun invoke(): LiveData<UiState<List<Music>>> =
    musicRepository.getMusics().asLiveData()
}