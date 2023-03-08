package com.omdeep.jetpackcompose.data.room.tables

import androidx.room.*

@Entity(tableName = "earning_table")
class Earnings(

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