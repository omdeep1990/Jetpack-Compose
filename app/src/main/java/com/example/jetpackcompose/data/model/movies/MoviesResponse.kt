package com.example.jetpackcompose.data.model.movies

import com.google.gson.annotations.SerializedName

data class MoviesResponse(

    @SerializedName("crew")
    val crew: String,

    @SerializedName("fullTitle")
    val fullTitle: String,

    @SerializedName("id")
    val id: String,

    @SerializedName("imDbRating")
    val imDbRating: String,

    @SerializedName("imDbRatingCount")
    val imDbRatingCount: String,

    @SerializedName("image")
    val image: String,

    @SerializedName("rank")
    val rank: String,

    @SerializedName("rankUpDown")
    val rankUpDown: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("year")
    val year: String

)