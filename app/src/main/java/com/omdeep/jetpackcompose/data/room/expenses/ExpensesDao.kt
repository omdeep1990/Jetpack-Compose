package com.omdeep.jetpackcompose.data.room.expenses

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ExpensesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExpenses(expenses: Expenses)

    @Query("Select * from expenses_table")
    fun getAllExpenses() : LiveData<List<Expenses>>
}