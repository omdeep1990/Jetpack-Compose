package com.omdeep.jetpackcompose.data.repository

import com.omdeep.jetpackcompose.BuildConfig.*
import com.omdeep.jetpackcompose.data.model.popularMovies.PopularMoviesResponse
import com.omdeep.jetpackcompose.data.network.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object ApiRepository {
    suspend fun getAllMovies() = RetrofitClient.getApiService().getMostPopularMovies(API_KEY)

    //Get TmDb popular movies: -
    fun getPopularMovies(callback : (result : List<PopularMoviesResponse>) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = RetrofitClient.getNewApiService(TMDB_BASE_URL).getPopularMovies(
                TMDB_API_KEY)

            withContext(Dispatchers.Main) {
                response.body()?.let {
                    callback(it.results)
                }
            }
        }
    }
}