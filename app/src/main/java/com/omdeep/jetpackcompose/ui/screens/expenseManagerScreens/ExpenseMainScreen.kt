package com.omdeep.jetpackcompose.ui.screens.expenseManagerScreens

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.omdeep.jetpackcompose.ui.navigation.ExpenseRoutes

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ExpenseMainScreen(
    navController: NavHostController = rememberNavController()
) {
    NavHost(navController = navController, startDestination = ExpenseRoutes.Ledger.route) {
        composable(ExpenseRoutes.Ledger.route) {
            MainExpenseManagerScreen(navController = navController)
        }

        composable(ExpenseRoutes.Earnings.route) {
            AddEarnings(navController = navController)
        }

        composable(ExpenseRoutes.Expenses.route) {
            AddExpenses(navController = navController)
        }

        composable(ExpenseRoutes.CurrentReport.route) {
            CurrentMonthReport(navController = navController)
        }

    }
}