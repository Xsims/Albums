package com.xsims.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.xsims.domain.models.Music
import com.xsims.domain.models.MusicMapper

@Entity
data class MusicEntity(

  @ColumnInfo(name = "albumId")
  @SerializedName("albumId")
  override val albumId: Int,

  @PrimaryKey(autoGenerate = false)
  @ColumnInfo(name = "id")
  @SerializedName("id")
  override val id: Int,

  @ColumnInfo(name = "title")
  @SerializedName("title")
  override val title: String,

  @ColumnInfo(name = "url")
  @SerializedName("url")
  override val url: String,

  @ColumnInfo(name = "thumbnailUrl")
  @SerializedName("thumbnailUrl")
  override val thumbnailUrl: String
) : Music {

  companion object : MusicMapper {
    override fun mapFrom(music: Music): Music =
      MusicEntity(
        albumId = music.albumId,
        id = music.id,
        title = music.title,
        url = music.url,
        thumbnailUrl = music.thumbnailUrl
      )
  }
}