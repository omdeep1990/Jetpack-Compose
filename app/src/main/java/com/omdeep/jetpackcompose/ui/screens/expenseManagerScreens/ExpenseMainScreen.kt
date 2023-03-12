package com.omdeep.jetpackcompose.ui.screens.expenseManagerScreens

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.omdeep.jetpackcompose.utils.Constants.EARNINGS
import com.omdeep.jetpackcompose.utils.Constants.EARNINGS_REPORT
import com.omdeep.jetpackcompose.utils.Constants.EXPENSES
import com.omdeep.jetpackcompose.utils.Constants.EXPENSES_REPORT
import com.omdeep.jetpackcompose.utils.Constants.GET_REPORT
import com.omdeep.jetpackcompose.utils.Constants.LEDGER

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ExpenseMainScreen(
    navController: NavHostController = rememberNavController()
) {
    NavHost(navController = navController, startDestination = LEDGER) {

        composable(LEDGER) {
            MainExpenseManagerScreen(navController = navController)
        }

        composable(EARNINGS) {
            AddEarnings(navController = navController)
        }

        composable(EXPENSES) {
            AddExpenses(navController = navController)
        }

        composable(GET_REPORT) {
            SearchReportByStartDateEndDate(navController = navController)
        }

        composable("$EARNINGS_REPORT/{start_date}/{end_date}") { navBackStack ->
            val startDate = navBackStack.arguments?.getString("start_date")
            val endDate = navBackStack.arguments?.getString("end_date")
            EarningsReportSelectedMonth(startDate!!, endDate!!, navController)
        }

        composable("$EXPENSES_REPORT/{start_date}/{end_date}") { navBackStack ->
            val startDate = navBackStack.arguments?.getString("start_date")
            val endDate = navBackStack.arguments?.getString("end_date")
            ExpensesReportSelectedMonth(startDate!!, endDate!!, navController)
        }

    }
}