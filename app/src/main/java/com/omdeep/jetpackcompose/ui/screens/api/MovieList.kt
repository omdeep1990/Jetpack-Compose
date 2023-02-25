package com.omdeep.jetpackcompose.ui.screens.api

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.omdeep.jetpackcompose.data.model.movies.MoviesResponse
import com.omdeep.jetpackcompose.ui.screens.api.MovieItem

@Composable
fun EmployeesList(listEmployees: List<MoviesResponse>){

    LazyColumn(){
        items(
            items = listEmployees,
            itemContent = { MovieItem(movie = it) }
        )
    }
}