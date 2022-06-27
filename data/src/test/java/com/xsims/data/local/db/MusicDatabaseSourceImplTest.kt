package com.xsims.data.local.db

import com.xsims.data.models.MusicEntity
import com.xsims.test.shared.TestUtils
import io.mockk.Runs
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import io.mockk.slot
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class MusicDatabaseSourceImplTest {
  private val musicDao: MusicDao = mockk()
  private val musicDatabaseSourceImplTest = MusicDatabaseSourceImpl(musicDao)

  @Before
  fun setUp() {
    clearAllMocks()
  }

  @Test
  fun `When music dao returns data Then musicDatabaseSourceImpl returns the same`() = runBlocking {
    val responseExpected = listOf(TestUtils.mockMusic())
    coEvery { musicDao.getAllMusics() } returns responseExpected

    assertEquals(responseExpected, musicDatabaseSourceImplTest.getAllMusics())
  }

  @Test
  fun `When data is inserted Then music dao insert is called`() = runBlocking {
    val slot = slot<List<MusicEntity>>()
    coEvery { musicDao.insertMusics(any()) } just Runs

    musicDatabaseSourceImplTest.insertMusics(listOf(TestUtils.mockMusic()))

    coVerify(exactly = 1) { musicDao.insertMusics(capture(slot)) }
    assertThat(TestUtils.mockMusic().toString(), `is`(slot.captured.first().toString()))
  }
}
