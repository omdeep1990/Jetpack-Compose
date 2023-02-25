package com.omdeep.jetpackcompose.ui.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.omdeep.jetpackcompose.data.model.FirebaseUser
import com.omdeep.jetpackcompose.utils.ErrorMessage
import java.lang.IllegalArgumentException

class FirebaseRegisterViewModel : ViewModel() {
    var regUser: ErrorMessage = ErrorMessage()

    private val auth: FirebaseAuth = Firebase.auth
    private var dbRef : DatabaseReference = FirebaseDatabase.getInstance().reference

    private val _name = MutableLiveData(regUser.name)
    val name: LiveData<String> = _name
    var isNameValid: MutableState<Boolean> = mutableStateOf(false)
    var nameErrMsg: MutableState<String> = mutableStateOf("")

    private val _mobileNo = MutableLiveData(regUser.mobileNo)
    val mobileNo: LiveData<String> = _mobileNo
    var isMobileNoValid: MutableState<Boolean> = mutableStateOf(false)
    var mobileNoErrMsg: MutableState<String> = mutableStateOf("")

    private val _email = MutableLiveData(regUser.email)
    val email: LiveData<String> = _email
    var isEmailValid: MutableState<Boolean> = mutableStateOf(false)
    var emailErrMsg: MutableState<String> = mutableStateOf("")

    private val _password = MutableLiveData(regUser.password)
    val password: LiveData<String> = _password
    var isPasswordValid: MutableState<Boolean> = mutableStateOf(false)
    var passwordErrMsg: MutableState<String> = mutableStateOf("")

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading


    // Update email
    fun updateNameOnValueChange(newName: String) {
        _name.value = newName
    }

    // Update email
    fun updateMobileNoOnValueChange(newMobileNo: String) {
        _mobileNo.value = newMobileNo
    }

    // Update email
    fun updateEmailOnValueChange(newEmail: String) {
        _email.value = newEmail
    }

    // Update password
    fun updatePasswordOnValueChange(newPassword: String) {
        _password.value = newPassword
    }

    // Register user
    fun registerUser(home: () -> Unit/*, imagePath: String*/) {
        if (_loading.value == false) {
            val name: String = _name.value ?: throw IllegalArgumentException("name expected")
            val mobileNo: String = _mobileNo.value ?: throw IllegalArgumentException("mobile no expected")
            val email: String = _email.value ?: throw IllegalArgumentException("email expected")
            val password: String =
                _password.value ?: throw IllegalArgumentException("password expected")
            _loading.value = true

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        addUserInformationToDatabase(name, mobileNo, email, /*imagePath,*/ auth.currentUser!!.uid)
                        home()
                    }
                    _loading.value = false
                }
        }
    }

    private fun addUserInformationToDatabase(name : String, mobileNo : String, email : String/*, imagePath : String*/, uid : String) {
        dbRef.child("user").child(uid).setValue(FirebaseUser(name, mobileNo, email/*, imagePath*/, uid))
    }
}