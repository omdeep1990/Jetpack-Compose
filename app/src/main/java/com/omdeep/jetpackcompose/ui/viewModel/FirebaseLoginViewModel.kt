package com.omdeep.jetpackcompose.ui.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.omdeep.jetpackcompose.utils.ErrorMessage
import java.lang.IllegalArgumentException

class FirebaseLoginViewModel : ViewModel() {
    var logUser: ErrorMessage = ErrorMessage()
    private val auth: FirebaseAuth = Firebase.auth

    /*private */val _email = MutableLiveData(logUser.email)
    val email: LiveData<String> = _email
    var isEmailValid: MutableState<Boolean> = mutableStateOf(false)
    var emailErrMsg: MutableState<String> = mutableStateOf("")

    private val _password = MutableLiveData(logUser.password)
    val password: LiveData<String> = _password
    var isPasswordValid: MutableState<Boolean> = mutableStateOf(false)
    var passwordErrMsg: MutableState<String> = mutableStateOf("")

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    // Update email
    fun updateEmailOnValueChange(newEmail: String) {
        _email.value = newEmail
    }

    // Update password
    fun updatePasswordOnValueChange(newPassword: String) {
        _password.value = newPassword
    }

    // Login user
    fun loginUser(home: () -> Unit) {
        if (_loading.value == false) {
            val email: String = _email.value ?: throw IllegalArgumentException("email expected")
            val password: String =
                _password.value ?: throw IllegalArgumentException("password expected")

            _loading.value = true

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        home()
                    }
                    _loading.value = false
                }
        }
    }
}