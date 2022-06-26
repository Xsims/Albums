package com.xsims.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.xsims.domain.models.Music

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
) : Music