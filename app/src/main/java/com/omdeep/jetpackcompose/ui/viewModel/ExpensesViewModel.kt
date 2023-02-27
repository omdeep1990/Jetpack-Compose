package com.omdeep.jetpackcompose.ui.viewModel

import android.text.TextUtils
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omdeep.jetpackcompose.data.repository.ExpensesRepository
import com.omdeep.jetpackcompose.data.room.earnings.Earnings
import com.omdeep.jetpackcompose.data.room.expenses.Expenses
import com.omdeep.jetpackcompose.utils.ErrorMessage
import com.omdeep.jetpackcompose.utils.Valid
import kotlinx.coroutines.launch

class ExpensesViewModel(private val repository: ExpensesRepository) : ViewModel() {
    var checkExp: ErrorMessage = ErrorMessage()

    var date: MutableState<String> = mutableStateOf(checkExp.selectedDate)
    var dateErrMsg: MutableState<String> = mutableStateOf("")

    var time: MutableState<String> = mutableStateOf(checkExp.selectedTime)
    var timeErrMsg: MutableState<String> = mutableStateOf("")

    var expenses: MutableState<String> = mutableStateOf(checkExp.earningsType)
    var earningsErrMsg: MutableState<String> = mutableStateOf("")

    var amount: MutableState<String> = mutableStateOf(checkExp.amount)
    var amountErrMsg: MutableState<String> = mutableStateOf("")

    var note: MutableState<String> = mutableStateOf(checkExp.note)

    fun validate(): Boolean {
        val isValidEarnings = TextUtils.isEmpty(expenses.value)
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

    private fun insertEarnings(expenses: Expenses) {
        viewModelScope.launch {
            repository.insertEarnings(expenses)
        }
    }

    fun fetchAllExpenses() : LiveData<List<Expenses>> {
        return repository.getEarnings
    }

    fun insertAllExpenses() {
        insertEarnings(Expenses(0, date.value, time.value, expenses.value, amount.value, note.value))
    }
}