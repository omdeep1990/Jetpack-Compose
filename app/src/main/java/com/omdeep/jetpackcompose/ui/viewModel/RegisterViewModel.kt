package com.omdeep.jetpackcompose.ui.viewModel

import android.text.TextUtils
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omdeep.jetpackcompose.data.repository.UserRepository
import com.omdeep.jetpackcompose.data.room.tables.User
import com.omdeep.jetpackcompose.utils.ErrorMessage
import com.omdeep.jetpackcompose.utils.Valid
import kotlinx.coroutines.launch

class RegisterViewModel(private val repository: UserRepository) : ViewModel() {
    var regUser: ErrorMessage = ErrorMessage()

    var userName: MutableState<String> = mutableStateOf(regUser.name)
    var userNameErrMsg: MutableState<String> = mutableStateOf("")

    var email: MutableState<String> = mutableStateOf(regUser.email)
    var emailErrMsg: MutableState<String> = mutableStateOf("")


    var password: MutableState<String> = mutableStateOf(regUser.password)
    var passwordErrMsg: MutableState<String> = mutableStateOf("")


    var confirmPassword: MutableState<String> = mutableStateOf(regUser.confirmPassword)
    var confirmPasswordErrMsg: MutableState<String> = mutableStateOf("")

    private fun insertUser(user: User) {
        viewModelScope.launch {
            repository.insertUser(user)
        }
    }

    fun validate(): Boolean {
        val isValidName = TextUtils.isEmpty(userName.value)
        val isValidEmail = Valid.isValidEmail(email.value)
        val isValidPassword = Valid.isValidPassword(password.value)
        val isValidConfirmPassword = Valid.isConfirmPassword(password.value, confirmPassword.value)

        if (isValidName) {
            userNameErrMsg.value = "Please enter name."
        } else {
            userNameErrMsg.value = ""
        }
        if (!isValidEmail) {
            emailErrMsg.value = "Input proper email id"
        } else {
            emailErrMsg.value = ""
        }
        if (!isValidPassword) {
            passwordErrMsg.value = "Password must be strong"

        } else {
            passwordErrMsg.value = ""
        }
        if (!isValidConfirmPassword) {
            confirmPasswordErrMsg.value = "Password did not match"
        } else {
            confirmPasswordErrMsg.value = ""
        }
        return !isValidName && isValidEmail && isValidPassword && isValidConfirmPassword
    }

    fun register() {
        regUser.name = userName.value
        regUser.email = email.value
        regUser.password = password.value
        regUser.confirmPassword = confirmPassword.value
        if (validate()) {
            insertUser(User(email.value, userName.value, password.value))
        }
    }

    private fun clearDetails() {

        userName.value = ""
        email.value = ""
        password.value = ""
        confirmPassword.value = ""
    }
}