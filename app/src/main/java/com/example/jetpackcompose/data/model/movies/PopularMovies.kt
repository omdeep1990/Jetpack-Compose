package com.example.jetpackcompose.data.model.movies

import com.google.gson.annotations.SerializedName

data class PopularMovies(

    @SerializedName("errorMessage")
    val errorMessage: String,

    @SerializedName("items")
    val moviesResponses: List<MoviesResponse>
)