package com.omdeep.jetpackcompose.utils

import android.util.Patterns
import com.omdeep.jetpackcompose.utils.Constants.PASSWORD_PATTERN
import java.util.regex.Pattern

object Valid{
    private val pattern = Pattern.compile(PASSWORD_PATTERN)

    fun isValidEmail(email : String) : Boolean{
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isValidPassword(password:String):Boolean{
        return pattern.matcher(password).matches()
    }

    fun isConfirmPassword(password : String, confirmPassword : String) : Boolean{
        return password == confirmPassword
    }
}