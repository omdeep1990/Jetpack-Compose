package com.omdeep.jetpackcompose.data.model.movies

import com.google.gson.annotations.SerializedName

data class MostPopularMovies(

    @SerializedName("errorMessage")
    val errorMessage: String,

    @SerializedName("items")
    val moviesResponses: List<MoviesResponse>
)