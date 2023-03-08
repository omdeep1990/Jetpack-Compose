package com.omdeep.jetpackcompose.data.room

import androidx.room.TypeConverter
import java.text.SimpleDateFormat

class DateTypeConvertors {

    @TypeConverter
    fun fromTimeStamp(timeStamp: Long?): String? {
        return timeStamp?.let { FORMATTER.format(timeStamp) }
    }

    @TypeConverter
    fun dateToTimeStamp(timeStamp: String?): Long? {
        return timeStamp?.let { FORMATTER.parse(it)?.time }
    }

    companion object {
        val FORMATTER = SimpleDateFormat("dd/MM/yyyy")
    }
}