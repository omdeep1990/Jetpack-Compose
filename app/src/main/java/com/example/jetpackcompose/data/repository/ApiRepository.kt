package com.example.jetpackcompose.data.repository

import com.example.jetpackcompose.BuildConfig.API_KEY
import com.example.jetpackcompose.data.network.RetrofitClient

object ApiRepository {
    suspend fun getAllMovies() = RetrofitClient.apiInterface.getPopularMovies(API_KEY)
}