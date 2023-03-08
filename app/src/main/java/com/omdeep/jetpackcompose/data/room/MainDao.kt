package com.omdeep.jetpackcompose.data.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.omdeep.jetpackcompose.data.room.tables.Earnings
import com.omdeep.jetpackcompose.data.room.tables.Expenses
import com.omdeep.jetpackcompose.data.room.tables.User

@Dao
interface MainDao{

    //Queries for Login and sign up in room database: -
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user : User)

    @Query("Select * from user_table where email =:email and Password =:password")
    suspend fun getUser(email : String, password : String) : User

    //Queries for earnings in room database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEarnings(earnings: Earnings)

    @Query("Select * from earning_table")
    fun getAllEarnings() : LiveData<List<Earnings>>

    @Query("SELECT * FROM earning_table WHERE Date BETWEEN :startDate AND :endDate")
    fun getDataBetweenDatesFromEarnings(startDate: Long, endDate: Long): LiveData<List<Earnings>>

    @Query("SELECT * FROM earning_table WHERE earning_table.Earnings_Type = 'Wages' AND earning_table.Date between :startDate AND :endDate")
    fun getEarningsWagesList(startDate: Long, endDate: Long): LiveData<List<Earnings>>

    @Query("SELECT * FROM earning_table WHERE earning_table.Earnings_Type = 'Interest' AND earning_table.Date between :startDate AND :endDate")
    fun getEarningsWagesInterestList(startDate: Long, endDate: Long): LiveData<List<Earnings>>

    @Query("SELECT * FROM earning_table WHERE earning_table.Earnings_Type = 'Profit' AND earning_table.Date between :startDate AND :endDate")
    fun getEarningsProfitList(startDate: Long, endDate: Long): LiveData<List<Earnings>>

    @Query("SELECT * FROM earning_table WHERE earning_table.Earnings_Type = 'Rent' AND earning_table.Date between :startDate AND :endDate")
    fun getEarningsRentList(startDate: Long, endDate: Long): LiveData<List<Earnings>>

    @Query("SELECT * FROM earning_table WHERE earning_table.Earnings_Type = 'Salary' AND earning_table.Date between :startDate AND :endDate")
    fun getEarningsSalaryList(startDate: Long, endDate: Long): LiveData<List<Earnings>>

    @Query("SELECT * FROM earning_table WHERE earning_table.Earnings_Type = 'Pensions' AND earning_table.Date between :startDate AND :endDate")
    fun getEarningsPensionsList(startDate: Long, endDate: Long): LiveData<List<Earnings>>

    @Query("SELECT * FROM earning_table WHERE earning_table.Earnings_Type = 'Others' AND earning_table.Date between :startDate AND :endDate")
    fun getEarningsOthersList(startDate: Long, endDate: Long): LiveData<List<Earnings>>

    //Queries for expenses in room database: -
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExpenses(expenses: Expenses)
    @Query("Select * from expenses_table")
    fun getAllExpenses() : LiveData<List<Expenses>>
    @Query("SELECT * FROM expenses_table WHERE Date BETWEEN :startDate AND :endDate")
    fun getDataBetweenDatesByExpenses(startDate: Long, endDate: Long): LiveData<List<Expenses>>

    @Query("SELECT * FROM expenses_table WHERE expenses_table.Earnings_Type = 'Milk' AND expenses_table.Date between :startDate AND :endDate")
    fun getExpensesMilkList(startDate: Long, endDate: Long): LiveData<List<Earnings>>

    @Query("SELECT * FROM expenses_table WHERE expenses_table.Earnings_Type = 'Fees' AND expenses_table.Date between :startDate AND :endDate")
    fun getExpensesFeesList(startDate: Long, endDate: Long): LiveData<List<Earnings>>

    @Query("SELECT * FROM expenses_table WHERE expenses_table.Earnings_Type = 'Travel' AND expenses_table.Date between :startDate AND :endDate")
    fun getExpensesTravelList(startDate: Long, endDate: Long): LiveData<List<Earnings>>

    @Query("SELECT * FROM expenses_table WHERE expenses_table.Earnings_Type = 'Grocery' AND expenses_table.Date between :startDate AND :endDate")
    fun getExpensesGroceryList(startDate: Long, endDate: Long): LiveData<List<Earnings>>

    @Query("SELECT * FROM expenses_table WHERE expenses_table.Earnings_Type = 'Rent' AND expenses_table.Date between :startDate AND :endDate")
    fun getExpensesRentList(startDate: Long, endDate: Long): LiveData<List<Earnings>>

    @Query("SELECT * FROM expenses_table WHERE expenses_table.Earnings_Type = 'Insurance' AND expenses_table.Date between :startDate AND :endDate")
    fun getExpensesInsuranceList(startDate: Long, endDate: Long): LiveData<List<Earnings>>

    @Query("SELECT * FROM expenses_table WHERE expenses_table.Earnings_Type = 'Milk' AND expenses_table.Date between :startDate AND :endDate")
    fun getExpensesTelephoneNetworkList(startDate: Long, endDate: Long): LiveData<List<Earnings>>

}
