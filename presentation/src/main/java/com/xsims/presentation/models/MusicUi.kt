package com.xsims.presentation.models

import com.xsims.domain.models.Music
import com.xsims.domain.models.MusicMapper

data class MusicUi(
  override val albumId: Int,
  override val id: Int,
  override val title: String,
  override val url: String,
  override val thumbnailUrl: String
) : Music {

  companion object : MusicMapper {
    override fun mapFrom(music: Music): Music =
      MusicUi(
        albumId = music.albumId,
        id = music.id,
        title = music.title,
        url = music.url,
        thumbnailUrl = music.thumbnailUrl
      )
  }
}