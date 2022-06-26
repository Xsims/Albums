package com.xsims.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.xsims.data.models.MusicEntity

@Dao
interface MusicDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertMusics(musics: List<MusicEntity>)

  @Query("SELECT * FROM musicEntity")
  fun getAllMusics(): List<MusicEntity>
}