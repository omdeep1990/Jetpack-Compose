package com.omdeep.jetpackcompose.data.network

import com.omdeep.jetpackcompose.data.model.movies.PopularMovies
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("MostPopularMovies/{apiKey}")
    suspend fun getPopularMovies(@Path("apiKey") apikey : String) : Response<PopularMovies>
}