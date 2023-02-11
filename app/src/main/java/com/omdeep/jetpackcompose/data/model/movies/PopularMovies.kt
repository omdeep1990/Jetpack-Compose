package com.omdeep.jetpackcompose.data.model.movies

import com.google.gson.annotations.SerializedName

data class PopularMovies(

    @SerializedName("errorMessage")
    val errorMessage: String,

    @SerializedName("items")
    val moviesResponses: List<com.omdeep.jetpackcompose.data.model.movies.MoviesResponse>
)