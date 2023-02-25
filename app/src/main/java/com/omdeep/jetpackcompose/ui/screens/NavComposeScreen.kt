package com.omdeep.jetpackcompose.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.omdeep.jetpackcompose.ui.theme.JetpackComposeTheme
import com.omdeep.jetpackcompose.ui.navigation.Action
import com.omdeep.jetpackcompose.ui.navigation.Destination.AuthenticationOption
import com.omdeep.jetpackcompose.ui.navigation.Destination.Home
import com.omdeep.jetpackcompose.ui.navigation.Destination.Login
import com.omdeep.jetpackcompose.ui.navigation.Destination.Register
import com.omdeep.jetpackcompose.ui.navigation.Destination.Users
import com.omdeep.jetpackcompose.ui.screens.firebase.AuthenticationView
import com.omdeep.jetpackcompose.ui.screens.firebase.LoginScreen
import com.omdeep.jetpackcompose.ui.screens.firebase.RegisterScreen
import com.omdeep.jetpackcompose.ui.screens.firebase.UsersListScreen

/**
 * The main Navigation composable which will handle all the navigation stack.
 */
@Composable
fun NavComposeApp(imagePath : String?) {
    val navController = rememberNavController()
    val actions = remember(navController) { Action(navController) }
    JetpackComposeTheme {
        NavHost(
            navController = navController,
            startDestination =
//            if (FirebaseAuth.getInstance().currentUser != null)
//                Home
//            else
                AuthenticationOption
        ) {
            composable(AuthenticationOption) {
                AuthenticationView(
                    register = actions.register,
                    login = actions.login
                )
            }
            composable(Register) {
                RegisterScreen(
                    imagePath = imagePath,
                    home = actions.home,
                    back = actions.navigateBack
                )
            }
            composable(Login) {
                LoginScreen(
                    home = actions.home,
                    back = actions.navigateBack
                )
            }
            composable(Home) {
                TabsMainScreen(navController)
            }
            composable(Users) {
                UsersListScreen(
                    home = actions.home
                )
            }
        }
    }
}