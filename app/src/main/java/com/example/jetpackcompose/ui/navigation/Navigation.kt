package com.example.jetpackcompose.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.jetpackcompose.data.model.NavDrawerItem
import com.example.jetpackcompose.ui.screens.*
import com.example.jetpackcompose.ui.viewModel.ApiViewModel

@Composable
fun Navigation(navController: NavHostController, viewModel : ApiViewModel, isLoading : Boolean) {
    NavHost(navController, startDestination = NavDrawerItem.Home.route) {
        composable(NavDrawerItem.Home.route) {
            HomeScreen(viewModel, isLoading)
        }
        composable(NavDrawerItem.Profile.route) {
            ProfileScreen()
        }
        composable(NavDrawerItem.Video.route) {
            VideoScreen()
        }
        composable(NavDrawerItem.Yoga.route) {
            YogaScreen()
        }
        composable(NavDrawerItem.About.route) {
            AboutScreen()
        }
    }
}