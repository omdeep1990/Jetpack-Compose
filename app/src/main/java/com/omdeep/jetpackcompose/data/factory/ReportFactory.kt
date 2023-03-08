package com.omdeep.jetpackcompose.data.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.omdeep.jetpackcompose.data.repository.EarningsRepository
import com.omdeep.jetpackcompose.data.repository.ExpensesRepository
import com.omdeep.jetpackcompose.ui.viewModel.ReportViewModel

class ReportFactory(private val earnRep : EarningsRepository, private val expRep : ExpensesRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ReportViewModel::class.java)) {
            return ReportViewModel(earnRep, expRep) as T
        } else {
            throw IllegalArgumentException("Unknown Class Found")
        }
    }
}