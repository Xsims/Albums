package com.xsims.domain.usecases

import com.xsims.common.models.UiState
import com.xsims.domain.models.Music
import com.xsims.domain.repositories.MusicRepository

class FetchMusicUseCase(
  private val musicRepository: MusicRepository
) {
  suspend fun getMusic(musicId: Int): UiState<Music> {
    return musicRepository.getMusic(musicId)
  }
}