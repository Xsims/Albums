package com.xsims.data.remote.api

import com.skydoves.sandwich.ApiResponse
import com.xsims.domain.models.Music

interface MusicService {
  suspend fun getMusics(): ApiResponse<List<Music>>
}