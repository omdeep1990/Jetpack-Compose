package com.omdeep.jetpackcompose.ui.viewModel

import android.text.TextUtils
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omdeep.jetpackcompose.data.repository.UserRepository
import com.omdeep.jetpackcompose.data.room.User
import com.omdeep.jetpackcompose.utils.ErrorMessage
import com.omdeep.jetpackcompose.utils.Valid
import kotlinx.coroutines.launch

class RegisterViewModel(private val repository: UserRepository) : ViewModel() {
    var regUser: ErrorMessage = ErrorMessage()

    var userName: MutableState<String> = mutableStateOf(regUser.name)
    var isUserNameValid: MutableState<Boolean> = mutableStateOf(false)
    var userNameErrMsg: MutableState<String> = mutableStateOf("")

    var email: MutableState<String> = mutableStateOf(regUser.email)
    var isEmailValid: MutableState<Boolean> = mutableStateOf(false)
    var emailErrMsg: MutableState<String> = mutableStateOf("")


    var password: MutableState<String> = mutableStateOf(regUser.password)
    var isPasswordValid: MutableState<Boolean> = mutableStateOf(false)
    var passwordErrMsg: MutableState<String> = mutableStateOf("")


    var confirmPassword: MutableState<String> = mutableStateOf(regUser.confirmPassword)
    var isConfirmPasswordValid: MutableState<Boolean> = mutableStateOf(false)
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
            isUserNameValid.value = true
            userNameErrMsg.value = "Please enter name."
        } else {
            isUserNameValid.value = false
            userNameErrMsg.value = ""
        }
        if (!isValidEmail) {
            isEmailValid.value = true
            emailErrMsg.value = "Input proper email id"
        } else {
            isEmailValid.value = false
            emailErrMsg.value = ""
        }
        if (!isValidPassword) {
            isPasswordValid.value = true
            passwordErrMsg.value = "Password must be strong"

        } else {
            isPasswordValid.value = false
            passwordErrMsg.value = ""
        }
        if (!isValidConfirmPassword) {
            isConfirmPasswordValid.value = true
            confirmPasswordErrMsg.value = "Password did not match"
        } else {
            isConfirmPasswordValid.value = false
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
//	     clearDetails()
        }
    }

    private fun clearDetails() {

        userName.value = ""
        email.value = ""
        password.value = ""
        confirmPassword.value = ""
    }
}