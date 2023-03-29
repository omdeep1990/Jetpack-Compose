package com.omdeep.jetpackcompose.data.model.popularMovies

import com.google.gson.annotations.SerializedName

data class PopularMovies(

    @SerializedName("page")
    val page: Int,

    @SerializedName("results")
    val results: List<PopularMoviesResponse>,

    @SerializedName("total_pages")
    val total_pages: Int,

    @SerializedName("total_results")
    val total_results: Int

)