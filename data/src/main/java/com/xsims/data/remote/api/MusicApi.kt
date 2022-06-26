package com.xsims.data.remote.api

import com.skydoves.sandwich.ApiResponse
import com.xsims.data.models.MusicEntity
import retrofit2.http.GET

interface MusicApi {

  @GET("img/shared/technical-test.json")
  suspend fun getMusics(): ApiResponse<List<MusicEntity>>

  companion object {
    const val BASE_URL = "https://static.leboncoin.fr/"
  }
}