package com.omdeep.jetpackcompose.utils

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Build
import android.widget.DatePicker
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.omdeep.jetpackcompose.utils.Constants.DATE_PATTERN
import com.omdeep.jetpackcompose.utils.Constants.monthList
import java.text.DateFormat
import java.text.Format
import java.text.SimpleDateFormat
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.util.*

object Calender {
    private val calendar: Calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    private val month = calendar.get(Calendar.MONTH)
    private val day = calendar.get(Calendar.DAY_OF_MONTH)
    var hour = calendar[Calendar.HOUR_OF_DAY]
    var minute = calendar[Calendar.MINUTE]
    private val monthDate = SimpleDateFormat("MMMM", Locale.US)
    val monthName: String = monthDate.format(calendar.time)

    fun openDatePickerDialog(
        context: Context, date: MutableState<String> = mutableStateOf("")
    ): DatePickerDialog {
        val datePickerDialog = DatePickerDialog(
            context, { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
                date.value = "$mDayOfMonth/${mMonth + 1}/$mYear"
            }, year, month, day
        )
        return datePickerDialog
    }

    fun openTimePickerDialog(
        context: Context, time: MutableState<String> = mutableStateOf("")
    ): TimePickerDialog {
        val timePickerDialog = TimePickerDialog(
            context, { _, mHour: Int, mMinute: Int ->
                time.value = getTime(mHour, mMinute)!!
            }, hour, minute, false
        )
        return timePickerDialog
    }

    private fun getTime(hr : Int, min : Int) : String? {
        hour = hr
        minute = min
        val formatter : Format = SimpleDateFormat("h:mm a", Locale.US)
        return formatter.format(calendar.time)
    }

    private fun getLastDayOfMonth(dateString: String?): Int {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val pattern = DateTimeFormatter.ofPattern(DATE_PATTERN)
            val yearMonth = YearMonth.parse(dateString, pattern)
            val date = yearMonth.atEndOfMonth()
            date.lengthOfMonth()
        } else {
            val dateFormat: DateFormat = SimpleDateFormat(DATE_PATTERN, Locale.US)
            val calender = Calendar.getInstance()
            calender.time = dateString?.let { dateFormat.parse(it) }!!
            calender.getActualMaximum(Calendar.DAY_OF_MONTH)
        }
    }

    fun getStartDate(monthName: MutableState<String>, yearName: MutableState<String>): String {
        return if (monthName.value != "" && yearName.value != "") {
            "01/${monthList.indexOf(monthName.value) + 1}/${yearName.value}"
        } else if (monthName.value == "" && yearName.value != "") {
            "01/${month + 1}/${yearName.value}"
        } else if (monthName.value != "" && yearName.value == "") {
            "01/${monthList.indexOf(monthName.value) + 1}/$year"
        } else {
            "01/${month + 1}/$year"
        }
    }

    fun getEndDate(monthName: MutableState<String>, yearName: MutableState<String>) : String {
        return if (monthName.value != "" && yearName.value != "") {
            val monthIndex = monthList.indexOf(monthName.value)
            if (monthIndex < 9) {
                "${getLastDayOfMonth("0${monthIndex + 1}/${yearName.value}")}/${monthIndex + 1}/${yearName.value}"
            } else {
                "${getLastDayOfMonth("${monthIndex + 1}/${yearName.value}")}/${monthIndex + 1}/${yearName.value}"
            }
        } else if (monthName.value == "" && yearName.value != "") {
            if (month < 9) {
                "${getLastDayOfMonth("0${month + 1}/${yearName.value}")}/${month + 1}/${yearName.value}"
            } else {
                "${getLastDayOfMonth("${month + 1}/${yearName.value}")}/${month + 1}/${yearName.value}"
            }
        } else if (monthName.value != "" && yearName.value == "") {
            val monthIndex = monthList.indexOf(monthName.value)
            if (monthIndex < 9) {
                "${getLastDayOfMonth("0${monthIndex + 1}/${year}")}/${monthIndex + 1}/$year"
            } else {
                "${getLastDayOfMonth("${monthIndex + 1}/$year")}/${monthIndex + 1}/$year"
            }
        } else {
            if (month < 9) {
                "${getLastDayOfMonth("0${month + 1}/$year")}/${month + 1}/$year"
            } else {
                "${getLastDayOfMonth("${month + 1}/$year")}/${month + 1}/$year"
            }
        }
    }

}