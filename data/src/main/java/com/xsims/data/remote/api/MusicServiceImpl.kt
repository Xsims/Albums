package com.xsims.data.remote.api

class MusicServiceImpl(
  private val musicApi: MusicApi
) : MusicService {
  override suspend fun getMusics() = musicApi.getMusics()
}