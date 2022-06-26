package com.xsims.domain.repositories

import com.xsims.common.models.UiState
import com.xsims.domain.models.Music
import kotlinx.coroutines.flow.Flow

interface MusicRepository {
  fun getMusics(): Flow<UiState<List<Music>>>
}