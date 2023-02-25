package com.omdeep.jetpackcompose.data.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.omdeep.jetpackcompose.data.repository.UserRepository
import com.omdeep.jetpackcompose.ui.viewModel.LoginViewModel

class LoginFactory(private val repository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(repository) as T
        } else {
            throw IllegalArgumentException("Unknown Class Found")
        }
    }
}