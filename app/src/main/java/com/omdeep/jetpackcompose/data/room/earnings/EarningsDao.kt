package com.omdeep.jetpackcompose.data.room.earnings

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface EarningsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEarnings(earnings: Earnings)

    @Query("Select * from earning_table")
    fun getAllEarnings() : LiveData<List<Earnings>>

    @Query("SELECT * FROM earning_table WHERE Date BETWEEN :startDate AND :endDate")
    fun getDataBetweenDates(startDate: String?, endDate: String?): LiveData<List<Earnings>>

//    @Query("SELECT * FROM earning_table WHERE Date BETWEEN CONCAT ('startDate') AND CONCAT ('endDate')")
//    fun getDataBetweenDates(startDate: String?, endDate: String?): LiveData<List<Earnings>>
}