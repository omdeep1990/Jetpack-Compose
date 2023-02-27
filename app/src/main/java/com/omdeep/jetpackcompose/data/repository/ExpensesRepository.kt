package com.omdeep.jetpackcompose.data.repository

import com.omdeep.jetpackcompose.data.room.expenses.Expenses
import com.omdeep.jetpackcompose.data.room.expenses.ExpensesDao

class ExpensesRepository(private val dao : ExpensesDao) {
    val getEarnings = dao.getAllExpenses()

    suspend fun insertEarnings(expenses: Expenses) {
        dao.insertExpenses(expenses)
    }
}