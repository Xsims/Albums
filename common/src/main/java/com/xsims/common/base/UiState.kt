package com.xsims.common.base

sealed class UiState<out R> {
  data class Success<out T>(val data: T) : UiState<T>()
  data class Error(val exception: Exception) : UiState<Nothing>() {
    val errorMessage = "${exception.message}"
  }

  object Loading : UiState<Nothing>()
}