package com.xsims.common.models

sealed class UiState<out R> {
  data class Success<out T>(val data: T) : UiState<T>()
  data class Error(val exception: Exception) : UiState<Nothing>() {
    val errorMessage = "${exception.message}"
  }

  object Loading : UiState<Nothing>()

  fun <T> mapData(lambda: (R) -> (T)): UiState<T> {
    return when (this) {
      is Success -> Success(lambda(data))
      is Loading -> Loading
      is Error -> Error(this.exception)
    }
  }
}