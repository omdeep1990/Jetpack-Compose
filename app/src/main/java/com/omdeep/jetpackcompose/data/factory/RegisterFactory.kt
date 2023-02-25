package com.omdeep.jetpackcompose.data.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.omdeep.jetpackcompose.data.repository.UserRepository
import com.omdeep.jetpackcompose.ui.viewModel.LoginViewModel
import com.omdeep.jetpackcompose.ui.viewModel.RegisterViewModel

class RegisterFactory(private val repository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            return RegisterViewModel(repository) as T
        } else {
            throw IllegalArgumentException("Unknown Class Found")
        }
    }
}