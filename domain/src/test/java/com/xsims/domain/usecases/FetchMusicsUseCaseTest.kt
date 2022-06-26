package com.xsims.domain.usecases

import com.xsims.common.models.UiState
import com.xsims.domain.models.Music
import com.xsims.domain.repositories.MusicRepository
import com.xsims.test.shared.TestCoroutineRule
import io.mockk.clearAllMocks
import io.mockk.mockk
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [24], manifest = Config.NONE)
class FetchMusicsUseCaseTest {
  @get:Rule
  val testCoroutineRule = TestCoroutineRule()

  private val fakeMusicRepository = FakeMusicRepository()
  private val music: Music = mockk()
  private val fetchMusicsUseCase = FetchMusicsUseCase(FakeMusicRepository())

  @Before
  fun setUp() {
    clearAllMocks()
  }

  @Test
  fun `When invoke method is called Then return a livedata from the repository flow`() =
    testCoroutineRule.runTest {
      // TODO : find a way to have livedata not null

      //    fakeMusicRepository.emit(UiState.Success(listOf(music)))
//    val returnValue = fetchMusicsUseCase.invoke().getOrAwaitValue()
//    assert(returnValue is UiState.Success)
    }

  class FakeMusicRepository : MusicRepository {
    private val flow = MutableSharedFlow<UiState<List<Music>>>()
    suspend fun emit(value: UiState<List<Music>>) = flow.emit(value)
    override fun getMusics(): Flow<UiState<List<Music>>> = flow
  }
}