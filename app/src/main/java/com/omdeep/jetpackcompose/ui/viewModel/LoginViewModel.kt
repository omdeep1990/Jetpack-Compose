package com.omdeep.jetpackcompose.ui.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omdeep.jetpackcompose.data.repository.UserRepository
import com.omdeep.jetpackcompose.data.room.User
import com.omdeep.jetpackcompose.utils.ErrorMessage
import com.omdeep.jetpackcompose.utils.Valid
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher

class LoginViewModel(private val repository: UserRepository) : ViewModel() {
    var reqUser: ErrorMessage = ErrorMessage()
    var userLiveData: MutableLiveData<User> = MutableLiveData()

    var email: MutableState<String> = mutableStateOf(reqUser.email)
    var isEmailValid: MutableState<Boolean> = mutableStateOf(false)
    var emailErrMsg: MutableState<String> = mutableStateOf("")


    var password: MutableState<String> = mutableStateOf(reqUser.password)
    var isPasswordValid: MutableState<Boolean> = mutableStateOf(false)
    var passwordErrMsg: MutableState<String> = mutableStateOf("")

    private fun getUser(email: String, password: String) {
        viewModelScope.launch {
            val user: User = repository.getUser(email, password)

            withContext(Dispatchers.Main) {
                userLiveData.value = user
            }
        }
    }

    private fun validate(): Boolean {
        val isValidEmail = Valid.isValidEmail(email.value)
        val isValidPassword = Valid.isValidPassword(password.value)
        if (!isValidEmail) {
            isEmailValid.value = true
            emailErrMsg.value = "Input proper email id"

        } else {
            isEmailValid.value = false
            emailErrMsg.value = ""
        }
        if (!isValidPassword) {
            isPasswordValid.value = true
            passwordErrMsg.value = "Password must be strong."

        } else {
            isPasswordValid.value = false
            passwordErrMsg.value = ""
        }
        return isValidEmail && isValidPassword

    }

    fun login() {
        reqUser.email = email.value
        reqUser.password = password.value
        if (validate()) {
            getUser(email.value, password.value)
            clearCredentials()
        }
    }

    private fun clearCredentials() {
        email.value = ""
        password.value = ""
    }
}