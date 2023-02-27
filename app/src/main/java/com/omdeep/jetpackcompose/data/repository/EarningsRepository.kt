package com.omdeep.jetpackcompose.data.repository

import androidx.lifecycle.LiveData
import com.omdeep.jetpackcompose.data.room.earnings.Earnings
import com.omdeep.jetpackcompose.data.room.earnings.EarningsDao

class EarningsRepository(private val dao: EarningsDao) {

    val getEarnings = dao.getAllEarnings()

    fun getEarningsByMonth(startDate : String, endDate : String) : LiveData<List<Earnings>> {
        return dao.getDataBetweenDates(startDate, endDate)
    }


    suspend fun insertEarnings(earnings: Earnings) {
        dao.insertEarnings(earnings)
    }

}