package com.omdeep.jetpackcompose.ui.screens.roomScreens

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.omdeep.jetpackcompose.data.factory.LoginFactory
import com.omdeep.jetpackcompose.data.factory.RegisterFactory
import com.omdeep.jetpackcompose.data.repository.UserRepository
import com.omdeep.jetpackcompose.data.room.MainDatabase
import com.omdeep.jetpackcompose.ui.viewModel.LoginViewModel
import com.omdeep.jetpackcompose.ui.viewModel.RegisterViewModel
import com.omdeep.jetpackcompose.ui.navigation.Routes

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ScreenMain(
    navController: NavHostController = rememberNavController(),
    lvm : LoginViewModel = viewModel(factory = LoginFactory(UserRepository(MainDatabase.getInstance(LocalContext.current).dao))),
    rvm : RegisterViewModel = viewModel(factory = RegisterFactory(UserRepository(MainDatabase.getInstance(LocalContext.current).dao))),
) {
    NavHost(navController = navController, startDestination = Routes.Login.route) {
        composable(Routes.Login.route) {
            LoginPage(navController = navController, lvm = lvm)
        }

        composable(Routes.SignUp.route) {
            SignUpPage(navController = navController, rvm = rvm)
        }
    }
}