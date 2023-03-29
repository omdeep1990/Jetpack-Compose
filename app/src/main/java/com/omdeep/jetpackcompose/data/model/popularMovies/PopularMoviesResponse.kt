package com.omdeep.jetpackcompose.data.model.popularMovies

import com.google.gson.annotations.SerializedName

data class PopularMoviesResponse(

    @SerializedName("adult")
    val adult: String,

    @SerializedName("backdrop_path")
    val backdrop_path: String? = null,

    @SerializedName("id")
    val id: String,

    @SerializedName("original_language")
    val original_language: String,

    @SerializedName("original_title")
    val original_title: String,

    @SerializedName("overview")
    val overview: String,

    @SerializedName("popularity")
    val popularity: String,

    @SerializedName("poster_path")
    val poster_path: String,

    @SerializedName("release_date")
    val release_date: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("video")
    val video: String,

    @SerializedName("vote_average")
    val vote_average: String,

    @SerializedName("vote_count")
    val vote_count: String
)