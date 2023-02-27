package com.omdeep.jetpackcompose.ui.viewModel

import android.text.TextUtils
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omdeep.jetpackcompose.data.repository.EarningsRepository
import com.omdeep.jetpackcompose.data.room.earnings.Earnings
import com.omdeep.jetpackcompose.data.room.loginSignUp.User
import com.omdeep.jetpackcompose.utils.ErrorMessage
import com.omdeep.jetpackcompose.utils.Valid
import kotlinx.coroutines.launch

class EarningsViewModel(private val repository: EarningsRepository) : ViewModel() {
    var checkEar: ErrorMessage = ErrorMessage()

    var date: MutableState<String> = mutableStateOf(checkEar.selectedDate)

    var time: MutableState<String> = mutableStateOf(checkEar.selectedTime)

    var earnings: MutableState<String> = mutableStateOf(checkEar.earningsType)
    var earningsErrMsg: MutableState<String> = mutableStateOf("")

    var amount: MutableState<String> = mutableStateOf(checkEar.amount)
    var amountErrMsg: MutableState<String> = mutableStateOf("")

    var note: MutableState<String> = mutableStateOf(checkEar.note)

    fun validate(): Boolean {
        val isValidEarnings = TextUtils.isEmpty(earnings.value)
        val isValidAmount = Valid.isValidEmail(amount.value)

        if (isValidEarnings) {
            earningsErrMsg.value = "Please enter name."
        } else {
            earningsErrMsg.value = ""
        }
        if (isValidAmount) {
            amountErrMsg.value = "Input proper email id"
        } else {
            amountErrMsg.value = ""
        }
        return !isValidEarnings && !isValidAmount
    }

    private fun insertEarnings(earnings: Earnings) {
        viewModelScope.launch {
            repository.insertEarnings(earnings)
        }
    }

    fun fetchAllEarnings() : LiveData<List<Earnings>> {
        return repository.getEarnings
    }

    fun fetchAllEarningsBYMonth(startDate : String, endDate : String) : LiveData<List<Earnings>> {
        return repository.getEarningsByMonth(startDate, endDate)
    }

    fun insertAllEarnings() {
        insertEarnings(Earnings(0, date.value, time.value, earnings.value, amount.value, note.value))
    }

}