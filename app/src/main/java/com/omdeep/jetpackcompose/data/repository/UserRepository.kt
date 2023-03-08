package com.omdeep.jetpackcompose.data.repository

import com.omdeep.jetpackcompose.data.room.tables.User
import com.omdeep.jetpackcompose.data.room.MainDao

class UserRepository(private val mainDao: MainDao){
    suspend fun getUser(email: String,password : String) : User {
        return mainDao.getUser(email,password)
    }

    suspend fun insertUser(user: User){
        mainDao.insertUser(user)
    }

}
