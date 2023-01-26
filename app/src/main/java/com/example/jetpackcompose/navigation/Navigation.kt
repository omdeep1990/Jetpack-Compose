package com.example.jetpackcompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.jetpackcompose.model.NavDrawerItem
import com.example.jetpackcompose.screens.*

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController, startDestination = NavDrawerItem.Home.route) {
        composable(NavDrawerItem.Home.route) {
            HomeScreen()
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