package com.omdeep.jetpackcompose.ui.navigation

sealed class ExpenseRoutes(val route: String) {
    object Ledger : ExpenseRoutes("Ledger")
    object Earnings : ExpenseRoutes("Earnings")
    object Expenses : ExpenseRoutes("Expenses")
    object CurrentReport : ExpenseRoutes("CurrentReport")
}