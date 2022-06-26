package com.xsims.data.remote.api

import com.skydoves.sandwich.ApiResponse.Success
import com.skydoves.sandwich.getOrThrow
import com.xsims.test.shared.TestUtils
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import retrofit2.Response
import kotlin.test.assertEquals

class MusicServiceImplTest {
  private val musicApi: MusicApi = mockk()
  private val musicServiceImplTest = MusicServiceImpl(musicApi)

  @Before
  fun setUp() {
    clearAllMocks()
  }

  @Test
  fun `When api return Success Then  music service returns success`() = runBlocking {
    val responseExpected = listOf(TestUtils.mockMusic())
    coEvery { musicApi.getMusics() } returns Success(Response.success(responseExpected))

    assert(musicServiceImplTest.getMusics() is Success)
    assertEquals(responseExpected, musicServiceImplTest.getMusics().getOrThrow())
  }
}