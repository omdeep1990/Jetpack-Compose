package com.omdeep.jetpackcompose.data.room

import androidx.room.*

@Entity(tableName = "user_table")
class User(

    @PrimaryKey
    val email : String,

    @ColumnInfo(name = "Name")
    val name : String,

    @ColumnInfo(name = "Password")
    val password : String

)


