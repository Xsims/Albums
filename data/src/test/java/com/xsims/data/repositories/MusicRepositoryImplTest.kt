package com.xsims.data.repositories

import com.skydoves.sandwich.ApiResponse.Failure
import com.skydoves.sandwich.ApiResponse.Success
import com.xsims.common.models.UiState
import com.xsims.data.local.db.MusicDatabaseSource
import com.xsims.data.remote.api.MusicService
import com.xsims.test.shared.TestUtils.mockMusic
import io.mockk.Runs
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Before
import org.junit.Test
import retrofit2.Response
import kotlin.test.assertEquals

class MusicRepositoryImplTest {
  private val musicDatabaseSource: MusicDatabaseSource = mockk()
  private val musicService: MusicService = mockk()
  private val musicRepositoryImpl = MusicRepositoryImpl(musicService, musicDatabaseSource)

  @Before
  fun setUp() {
    clearAllMocks()
  }

  @Test
  fun `When getMusics is called Then first flow emit is UiState Loading`() = runBlocking {
    coEvery { musicService.getMusics() } returns Success(Response.success(listOf(mockMusic())))
    coEvery { musicDatabaseSource.insertMusics(any()) } just Runs

    assert(musicRepositoryImpl.getMusics().first() is UiState.Loading)
  }

  @Test
  fun `When music service returns musics Then UiState Success is return`() = runBlocking {
    val dataExpected = listOf(mockMusic())
    val responseExpected = Success(Response.success(dataExpected))

    coEvery { musicService.getMusics() } returns responseExpected
    coEvery { musicDatabaseSource.insertMusics(any()) } just Runs

    val response = musicRepositoryImpl.getMusics().last()

    assert(response is UiState.Success)
    assertThat(((response as UiState.Success).data.toString()), `is`(dataExpected.toString()))
  }

  @Test
  fun `When service throws Exception and db has musics Then Success is return`() = runBlocking {
    val dataExpected = listOf(mockMusic())

    coEvery { musicService.getMusics() } returns Failure.Exception(Exception(""))
    coEvery { musicDatabaseSource.getAllMusics() } returns dataExpected

    val response = musicRepositoryImpl.getMusics().last()

    assert(response is UiState.Success)
    assertThat(((response as UiState.Success).data.toString()), `is`(dataExpected.toString()))
  }

  @Test
  fun `When service throws Exception and db is empty Then Error is return`() = runBlocking {
    val exceptionMsg =
      "If you wake up at a different time, in a different place, could you wake up as a different person?"
    coEvery { musicService.getMusics() } returns Failure.Exception(Exception(exceptionMsg))
    coEvery { musicDatabaseSource.getAllMusics() } returns emptyList()

    val response = musicRepositoryImpl.getMusics().last()
    assert(response is UiState.Error)
    assertEquals(exceptionMsg, (response as UiState.Error).errorMessage)
  }
}