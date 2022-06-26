package com.xsims.presentation.models

import com.xsims.domain.models.Music

data class MusicUi(
  override val albumId: Int,
  override val id: Int,
  override val title: String,
  override val url: String,
  override val thumbnailUrl: String
) : Music