package com.omdeep.jetpackcompose.data.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.omdeep.jetpackcompose.data.repository.DataStoreRepository
import com.omdeep.jetpackcompose.ui.viewModel.DataStoreViewModel

class DataStoreFactory(private val repository: DataStoreRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DataStoreViewModel::class.java)) {
            return DataStoreViewModel(repository) as T
        } else {
            throw IllegalArgumentException("Unknown Class Found")
        }
    }
}