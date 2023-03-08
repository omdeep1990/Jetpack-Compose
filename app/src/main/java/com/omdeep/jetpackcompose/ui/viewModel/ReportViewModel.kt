package com.omdeep.jetpackcompose.ui.viewModel

import android.text.TextUtils
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.omdeep.jetpackcompose.data.repository.EarningsRepository
import com.omdeep.jetpackcompose.data.repository.ExpensesRepository
import com.omdeep.jetpackcompose.utils.ErrorMessage

class ReportViewModel(private val earnRep : EarningsRepository, private val expRep : ExpensesRepository) : ViewModel() {
    var errMsg: ErrorMessage = ErrorMessage()

    var startDate: MutableState<String> = mutableStateOf(errMsg.selectedDate)
    var isValidStartDate : MutableState<Boolean> = mutableStateOf(false)
    var startDateErrMsg: MutableState<String> = mutableStateOf("")

    var endDate: MutableState<String> = mutableStateOf(errMsg.selectedTime)
    var isValidEndDate : MutableState<Boolean> = mutableStateOf(false)
    var endDateErrorMsg: MutableState<String> = mutableStateOf("")


    fun validate(): Boolean {
        val isStartDateValid = TextUtils.isEmpty(startDate.value)
        val isEndDateValid = TextUtils.isEmpty(endDate.value)

        if (isStartDateValid) {
            isValidStartDate.value = true
            startDateErrMsg.value = "Please select any date."
        } else {
            isValidStartDate.value = false
            startDateErrMsg.value = ""
        }
        if (isEndDateValid) {
            isValidEndDate.value = true
            endDateErrorMsg.value = "Please select any date."
        } else {
            isValidEndDate.value = false
            endDateErrorMsg.value = ""
        }
        return !isStartDateValid && !isEndDateValid
    }
}