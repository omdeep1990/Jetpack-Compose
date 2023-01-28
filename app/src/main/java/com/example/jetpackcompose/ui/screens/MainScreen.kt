package com.example.jetpackcompose.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.DrawerValue
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberDrawerState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.navigation.compose.rememberNavController
import com.example.jetpackcompose.R
import com.example.jetpackcompose.ui.navigation.BottomBar
import com.example.jetpackcompose.ui.navigation.Drawer
import com.example.jetpackcompose.ui.navigation.Navigation
import com.example.jetpackcompose.ui.navigation.TopBar
import com.example.jetpackcompose.ui.viewModel.ApiViewModel

@Composable
fun MainScreen(viewModel : ApiViewModel, isLoading : Boolean) {
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()
    // If you want the drawer from the right side, uncomment the following
    // CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { TopBar(scope = scope, scaffoldState = scaffoldState) },
        bottomBar = { BottomBar(navController = navController) },
        drawerBackgroundColor = colorResource(id = R.color.light_blue),
        drawerContent = {
            Drawer(scope = scope, scaffoldState = scaffoldState, navController = navController)
        },
        backgroundColor = colorResource(id = R.color.light_blue)
    ) { padding ->  // We need to pass scaffold's inner padding to content. That's why we use Box.
        Box(modifier = Modifier.padding(padding)) {
            Navigation(navController = navController, viewModel, isLoading)
        }
    }
    // }
}