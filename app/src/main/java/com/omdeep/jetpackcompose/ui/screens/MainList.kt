package com.omdeep.jetpackcompose.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import com.omdeep.jetpackcompose.ui.viewModel.ApiViewModel

@ExperimentalFoundationApi
@Composable
fun MainList(
    mainViewModel: ApiViewModel
) {
    EmployeesList(listEmployees = mainViewModel.movies)
}