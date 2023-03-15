package com.omdeep.jetpackcompose.ui.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omdeep.jetpackcompose.data.repository.UserRepository
import com.omdeep.jetpackcompose.data.room.tables.User
import com.omdeep.jetpackcompose.utils.ErrorMessage
import com.omdeep.jetpackcompose.utils.Valid
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel(private val repository: UserRepository) : ViewModel() {
    private var reqUser: ErrorMessage = ErrorMessage()
    var userLiveData: MutableLiveData<User> = MutableLiveData()

    var email: MutableState<String> = mutableStateOf(reqUser.email)
    var emailErrMsg: MutableState<String> = mutableStateOf("")


    var password: MutableState<String> = mutableStateOf(reqUser.password)
    var passwordErrMsg: MutableState<String> = mutableStateOf("")

    var isEnabled: MutableState<Boolean> = mutableStateOf(false)


    private fun getUser(email: String, password: String) {
        viewModelScope.launch {
            val user: User = repository.getUser(email, password)

            withContext(Dispatchers.Main) {
                userLiveData.value = user
            }
        }
    }

    fun validate(): Boolean {
        val isValidEmail = Valid.isValidEmail(email.value)
        val isValidPassword = Valid.isValidPassword(password.value)
        if (!isValidEmail) {
            emailErrMsg.value = "Input proper email id"

        } else {
            emailErrMsg.value = ""
        }
        if (!isValidPassword) {
            passwordErrMsg.value = "Password must be strong."

        } else {
            passwordErrMsg.value = ""
        }
        return isValidEmail && isValidPassword

    }

    fun login() {
        reqUser.email = email.value
        reqUser.password = password.value
        if (validate()) {
            getUser(email.value, password.value)
//            clearCredentials()
        }
    }

    private fun clearCredentials() {
        email.value = ""
        password.value = ""
    }

    //TODO: Below are the methods to to validate the textfields on realtime basis: -
    //TODO: 1. Validate Email onValueChange by putting this method in onValueChange: -
    fun validateEmail(): Boolean {
        val isValidEmail = Valid.isValidEmail(email.value)
        if (!isValidEmail) {
            emailErrMsg.value = "Input proper email id"
        } else {
            emailErrMsg.value = ""
        }
        return isValidEmail
    }

    //TODO: 2. Validate Password onValueChange by putting this method in onValueChange: -

    fun validatePassword(): Boolean {
        val isValidPassword = Valid.isValidPassword(password.value)
        if (!isValidPassword) {
            passwordErrMsg.value = "Password must be strong."
        } else {
            passwordErrMsg.value = ""
        }
        return isValidPassword
    }

    //TODO: 3. Button should be enabled or not, put isEnabled in enabled = isEnabled.value in button and initialize shouldEnabled(): -
    fun shouldEnabled(): Boolean {
        isEnabled.value = validateEmail() && validatePassword()
        return isEnabled.value
    }

    //TODO: 4. Login: -
    fun newLogin() {
        reqUser.email = email.value
        reqUser.password = password.value
        getUser(email.value, password.value)
    }

}