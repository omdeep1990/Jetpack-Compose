package com.omdeep.jetpackcompose.data.repository

import com.omdeep.jetpackcompose.data.room.User
import com.omdeep.jetpackcompose.data.room.UserDao

class UserRepository(private val userDao: UserDao){

    suspend fun getUser(email: String,password : String) : User {
        return userDao.getUser(email,password)
    }

    suspend fun insertUser(user:User){
        userDao.insertUser(user)
    }

}
