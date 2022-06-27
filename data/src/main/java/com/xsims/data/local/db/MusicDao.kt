package com.xsims.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.xsims.data.models.MusicEntity

@Dao
interface MusicDao {

  // TODO : add suspend without AS triggers false positive error due to room lib version
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertMusics(musics: List<MusicEntity>)

  // TODO : add suspend without AS triggers false positive error due to room lib version
  @Query("SELECT * FROM musicEntity")
  fun getAllMusics(): List<MusicEntity>

  // TODO : add suspend without AS triggers false positive error due to room lib version
  @Query("SELECT * FROM musicEntity WHERE id = :musicId")
  fun getMusic(musicId: Int): MusicEntity

}