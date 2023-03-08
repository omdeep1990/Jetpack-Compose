package com.omdeep.jetpackcompose.data.room.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "expenses_table")
class Expenses(

    @PrimaryKey(autoGenerate = true)
    val srNo : Int,

    @ColumnInfo(name = "Date")
    val date : Long,

    @ColumnInfo(name = "Time")
    val time : String,

    @ColumnInfo(name = "Earnings_Type")
    val earningsType : String,

    @ColumnInfo(name = "Amount")
    val amount : String,

    @ColumnInfo(name = "Note")
    val note : String

)