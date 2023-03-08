package com.omdeep.jetpackcompose.ui.screens.mlKit

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.omdeep.jetpackcompose.ui.navigation.ExpenseRoutes
import com.omdeep.jetpackcompose.ui.screens.expenseManagerScreens.MainExpenseManagerScreen

@Composable
fun MlKitScreen(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = "bar_code_scanner") {
        composable("bar_code_scanner") {
            BarCodeScannerScreen(navController = navController)
        }

        composable("camera_preview") {
            CameraPreview(navController = navController)
        }

    }
}