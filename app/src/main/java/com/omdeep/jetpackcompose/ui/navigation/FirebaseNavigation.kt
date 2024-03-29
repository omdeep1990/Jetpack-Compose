package com.omdeep.jetpackcompose.ui.navigation

import androidx.navigation.NavHostController
import com.omdeep.jetpackcompose.ui.navigation.Destination.CameraXScreen
import com.omdeep.jetpackcompose.ui.navigation.Destination.Home
import com.omdeep.jetpackcompose.ui.navigation.Destination.Login
import com.omdeep.jetpackcompose.ui.navigation.Destination.Register
import com.omdeep.jetpackcompose.ui.navigation.Destination.Users

/**
 * A set of destination used in the whole application
 */
object Destination {
    const val AuthenticationOption = "authenticationOption"
    const val Register = "register"
    const val Login = "login"
    const val Home = "home"
    const val Users = "users"
    const val CameraXScreen = "camera"
}

class Action(navController: NavHostController) {
    val home: () -> Unit = {
        navController.navigate(Home) {
            popUpTo(Login) {
                inclusive = true
            }
            popUpTo(Register) {
                inclusive = true
            }
            popUpTo(Users) {
                inclusive = true
            }
            popUpTo(CameraXScreen) {
                inclusive = true
            }
        }
    }
    val login: () -> Unit = { navController.navigate(Login) }
    val register: () -> Unit = { navController.navigate(Register) }
    val navigateBack: () -> Unit = { navController.popBackStack() }
//    val users: () -> Unit = { navController.navigate(Users) }
}