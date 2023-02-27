package com.omdeep.jetpackcompose.data.repository

import com.omdeep.jetpackcompose.data.room.loginSignUp.User
import com.omdeep.jetpackcompose.data.room.loginSignUp.UserDao

class UserRepository(private val userDao: UserDao){
    suspend fun getUser(email: String,password : String) : User {
        return userDao.getUser(email,password)
    }

    suspend fun insertUser(user: User){
        userDao.insertUser(user)
    }

}
