package com.omdeep.jetpackcompose.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.omdeep.jetpackcompose.data.model.movies.MoviesResponse

@Composable
fun EmployeesList(listEmployees: List<MoviesResponse>){

    LazyColumn(){
        items(
            items = listEmployees,
            itemContent = { MovieItem(movie = it) }
        )
    }
}