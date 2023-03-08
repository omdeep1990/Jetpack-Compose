package com.omdeep.jetpackcompose.data.repository

import androidx.lifecycle.LiveData
import com.omdeep.jetpackcompose.data.room.tables.Expenses
import com.omdeep.jetpackcompose.data.room.MainDao

class ExpensesRepository(private val dao : MainDao) {
    val getEarnings = dao.getAllExpenses()

        fun getExpensesByMonth(startDate : Long, endDate : Long) : LiveData<List<Expenses>> {
        return dao.getDataBetweenDatesByExpenses(startDate, endDate)
    }

    suspend fun insertEarnings(expenses: Expenses) {
        dao.insertExpenses(expenses)
    }
}