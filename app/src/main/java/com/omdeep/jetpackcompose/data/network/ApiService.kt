package com.omdeep.jetpackcompose.data.network

import com.omdeep.jetpackcompose.data.model.movies.MostPopularMovies
import com.omdeep.jetpackcompose.data.model.popularMovies.PopularMovies
import com.omdeep.jetpackcompose.data.model.popularMovies.PopularMoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("MostPopularMovies/{apiKey}")
    suspend fun getMostPopularMovies(@Path("apiKey") apikey : String) : Response<MostPopularMovies>

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("api_key") apiKey : String) : Response<PopularMovies>
}