package com.xsims.data.local.db

import com.xsims.domain.models.Music

interface MusicDatabaseSource {
  suspend fun insertMusics(musics: List<Music>)

  suspend fun getAllMusics(): List<Music>

  suspend fun getMusic(musicId: Int): Music
}