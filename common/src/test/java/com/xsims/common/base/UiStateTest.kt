package com.xsims.common.base

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class UiStateTest {
  @Test
  fun `When passing Exception to UiState Error Then errorMessage equals to exception message`() {
    val exceptionMsg = "exception"
    val errorUiState = UiState.Error(Exception(exceptionMsg))
    assertEquals(exceptionMsg, errorUiState.errorMessage)
  }

  @Test
  fun `When passing variable to UiState Success Then data is equal to variable`() {
    val data = "data"
    val successUiState = UiState.Success(data)
    assertEquals(data, successUiState.data)
  }

  @Test
  fun `Checking UiState Loading is inheritance from UiState`() {
    val loadingUiState: UiState<Nothing> = UiState.Loading
    assertTrue(loadingUiState is UiState.Loading)
  }
}