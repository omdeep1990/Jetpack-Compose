package com.omdeep.jetpackcompose.data.repository

import com.omdeep.jetpackcompose.BuildConfig.API_KEY
import com.omdeep.jetpackcompose.data.network.RetrofitClient

object ApiRepository {
    suspend fun getAllMovies() = RetrofitClient.apiInterface.getPopularMovies(API_KEY)
}