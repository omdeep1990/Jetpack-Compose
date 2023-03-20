package com.omdeep.jetpackcompose.ui.screens.dataStore

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.omdeep.jetpackcompose.ui.screens.expenseManagerScreens.*
import com.omdeep.jetpackcompose.utils.Constants.PREF_DATA_STORE
import com.omdeep.jetpackcompose.utils.Constants.PROTO_DATA_STORE

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun DataStoreMainScreen(
    navController: NavHostController = rememberNavController()
) {
    NavHost(navController = navController, startDestination = PREF_DATA_STORE) {

        composable(PREF_DATA_STORE) {
            PreferenceScreen(navController = navController)
        }

        composable(PROTO_DATA_STORE) {
            ProtoScreen(navController = navController)
        }

    }
}