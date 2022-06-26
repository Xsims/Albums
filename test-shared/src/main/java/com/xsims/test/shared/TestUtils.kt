package com.xsims.test.shared

import com.xsims.data.models.MusicEntity

object TestUtils {
  fun mockMusic(
    albumId: Int = 1,
    id: Int = 1,
    title: String = "title",
    url: String = "url",
    thumbnailUrl: String = "thumbnailUrl"
  ) =
    MusicEntity(
      albumId = albumId,
      id = id,
      title = title,
      url = url,
      thumbnailUrl = thumbnailUrl
    )
}