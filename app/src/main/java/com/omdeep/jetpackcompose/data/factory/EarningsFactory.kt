package com.omdeep.jetpackcompose.data.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.omdeep.jetpackcompose.data.repository.EarningsRepository
import com.omdeep.jetpackcompose.ui.viewModel.EarningsViewModel

class EarningsFactory(private val repository: EarningsRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EarningsViewModel::class.java)) {
            return EarningsViewModel(repository) as T
        } else {
            throw IllegalArgumentException("Unknown Class Found")
        }
    }
}