package com.omdeep.jetpackcompose.utils

import android.os.Build
import androidx.annotation.RequiresApi
import com.omdeep.jetpackcompose.data.room.DateTypeConvertors
import java.text.SimpleDateFormat
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.*

object Convertors {

    fun convertDateToLong(date : String) : Long {
        val df = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return df.parse(date)!!.time
    }


    fun convertDateToString(timeStamp: Long?): String? {
        return timeStamp?.let { DateTypeConvertors.FORMATTER.format(timeStamp) }
    }

}