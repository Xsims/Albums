@file:Suppress("UNCHECKED_CAST")

package com.xsims.data.local.db

import com.xsims.data.models.MusicEntity
import com.xsims.domain.models.Music

class MusicDatabaseSourceImpl(private val musicDao: MusicDao) : MusicDatabaseSource {
  override suspend fun insertMusics(musics: List<Music>) {
    return musicDao.insertMusics(musics as List<MusicEntity>)
  }

  override suspend fun getAllMusics(): List<Music> {
    return musicDao.getAllMusics()
  }

  override suspend fun getMusic(musicId: Int): Music {
    return musicDao.getMusic(musicId)
  }
}