package com.xsims.data.local.db

import com.xsims.test.shared.TestUtils.mockMusic
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [24], manifest = Config.NONE)
class MusicDaoTest : TestDatabase() {

  private lateinit var musicDao: MusicDao

  @Before
  fun init() {
    musicDao = db.musicDao()
  }

  @Test
  fun `When music is inserted in database Then it can be loaded`() = runBlocking {
    val mockDataList = listOf(mockMusic())
    musicDao.insertMusics(mockDataList)

    val loadFromDB = musicDao.getAllMusics()
    assertThat(loadFromDB.toString(), `is`(mockDataList.toString()))

    val mockData = mockMusic()
    assertThat(loadFromDB.first().toString(), `is`(mockData.toString()))
  }
}