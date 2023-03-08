package com.omdeep.jetpackcompose.data.repository

import androidx.lifecycle.LiveData
import com.omdeep.jetpackcompose.data.room.tables.Earnings
import com.omdeep.jetpackcompose.data.room.MainDao

class EarningsRepository(private val dao: MainDao) {

    val getEarnings = dao.getAllEarnings()

    fun getEarningsByMonth(startDate : Long, endDate : Long) : LiveData<List<Earnings>> {
        return dao.getDataBetweenDatesFromEarnings(startDate, endDate)
    }

    suspend fun insertEarnings(earnings: Earnings) {
        dao.insertEarnings(earnings)
    }
}