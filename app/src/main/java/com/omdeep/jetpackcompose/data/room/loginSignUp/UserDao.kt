package com.omdeep.jetpackcompose.data.room.loginSignUp

import androidx.room.*

@Dao
interface UserDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user : User)

    @Query("Select * from user_table where email =:email and Password =:password")
    suspend fun getUser(email : String, password : String) : User

}
