package com.omdeep.jetpackcompose.ui.screens.api

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.omdeep.jetpackcompose.data.model.movies.MoviesResponse
import com.omdeep.jetpackcompose.ui.screens.SwipeBackground
import com.omdeep.jetpackcompose.ui.screens.api.MovieItem

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun EmployeesList(listEmployees: List<MoviesResponse>){

    LazyColumn(){
        items(
            items = listEmployees,
            key = { it.id },
            itemContent = { it ->
                val currentItem by rememberUpdatedState(it)
                val dismissState = rememberDismissState(
                    confirmStateChange = {
                        (it == DismissValue.DismissedToStart) || (it == DismissValue.DismissedToEnd)
                    }
                )

                if (dismissState.isDismissed(DismissDirection.EndToStart) ||
                    dismissState.isDismissed(DismissDirection.StartToEnd)){
//                    viewModel.removeRecord(item)
                }


                SwipeToDismiss(
                    state = dismissState,
                    modifier = Modifier
                        .padding(vertical = 1.dp)
                        .animateItemPlacement(),
                    directions = setOf(
                        DismissDirection.StartToEnd,
                        DismissDirection.EndToStart
                    ),
                    dismissThresholds = { direction ->
                        FractionalThreshold(
                            if (direction == DismissDirection.StartToEnd) 0.66f else 0.50f
                        )
                    },
                    background = {
                        SwipeBackground(dismissState)
                    },
                    dismissContent = {
                        MovieItem(movie = it)
                    }
                )

            }
        )
    }
}