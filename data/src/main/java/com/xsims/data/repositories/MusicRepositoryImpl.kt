package com.xsims.data.repositories

import androidx.annotation.WorkerThread
import com.skydoves.sandwich.suspendOnException
import com.skydoves.sandwich.suspendOnSuccess
import com.xsims.common.models.UiState
import com.xsims.data.local.db.MusicDatabaseSource
import com.xsims.data.remote.api.MusicService
import com.xsims.domain.models.Music
import com.xsims.domain.repositories.MusicRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart

class MusicRepositoryImpl(
  private val musicService: MusicService,
  private val musicDatabaseSource: MusicDatabaseSource
) : MusicRepository {

  @WorkerThread
  override fun getMusics(): Flow<UiState<List<Music>>> =
    flow {
      musicService.getMusics()
        .suspendOnSuccess {
          musicDatabaseSource.insertMusics(data)
          emit(UiState.Success(data))
        }
        .suspendOnException {
          val musicFromDb = musicDatabaseSource.getAllMusics()
          if (musicFromDb.isNotEmpty())
            emit(UiState.Success(musicFromDb))
          else
            emit(UiState.Error(exception as Exception))
        }
    }.onStart { emit(UiState.Loading) }.flowOn(Dispatchers.IO)

  @WorkerThread
  override suspend fun getMusic(musicId: Int): UiState<Music> {
    return try {
      UiState.Success(musicDatabaseSource.getMusic(musicId))
    } catch (exception: Exception) {
      UiState.Error(exception)
    }
  }
}