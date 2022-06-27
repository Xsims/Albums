package com.xsims.domain.usecases

import com.xsims.common.models.UiState
import com.xsims.domain.models.Music
import com.xsims.domain.repositories.MusicRepository
import kotlinx.coroutines.flow.Flow

class FetchMusicsUseCase(
  private val musicRepository: MusicRepository
) {
  operator fun invoke(): Flow<UiState<List<Music>>> =
    musicRepository.getMusics()
}