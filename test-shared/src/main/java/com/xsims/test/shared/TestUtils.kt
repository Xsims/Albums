package com.xsims.test.shared

import com.xsims.data.models.MusicEntity

object TestUtils {
  fun mockMusic(
    albumId: Int = 1,
    id: Int = 1,
    title: String = "Say My Name.",
    url: String = "Kakarot!",
    thumbnailUrl: String = "Des kamas contre un code audio ?"
  ) =
    MusicEntity(
      albumId = albumId,
      id = id,
      title = title,
      url = url,
      thumbnailUrl = thumbnailUrl
    )
}