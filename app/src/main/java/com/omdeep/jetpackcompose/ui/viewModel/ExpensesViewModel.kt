package com.omdeep.jetpackcompose.ui.viewModel

import android.text.TextUtils
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omdeep.jetpackcompose.data.repository.ExpensesRepository
import com.omdeep.jetpackcompose.data.room.tables.Expenses
import com.omdeep.jetpackcompose.utils.Convertors.convertDateToLong
import com.omdeep.jetpackcompose.utils.ErrorMessage
import com.omdeep.jetpackcompose.utils.Valid
import kotlinx.coroutines.launch

class ExpensesViewModel(private val repository: ExpensesRepository) : ViewModel() {
    var checkExp: ErrorMessage = ErrorMessage()

    var date: MutableState<String> = mutableStateOf(checkExp.selectedDate)
    var isValidDate : MutableState<Boolean> = mutableStateOf(false)
    var dateErrMsg: MutableState<String> = mutableStateOf("")

    var time: MutableState<String> = mutableStateOf(checkExp.selectedTime)
    var isValidTime : MutableState<Boolean> = mutableStateOf(false)
    var timeErrMsg: MutableState<String> = mutableStateOf("")

    var expenses: MutableState<String> = mutableStateOf(checkExp.earningsType)
    var isValidExpenses : MutableState<Boolean> = mutableStateOf(false)
    var expensesErrMsg: MutableState<String> = mutableStateOf("")

    var amount: MutableState<String> = mutableStateOf(checkExp.amount)
    var isValidAmount : MutableState<Boolean> = mutableStateOf(false)
    var amountErrMsg: MutableState<String> = mutableStateOf("")

    var note: MutableState<String> = mutableStateOf(checkExp.note)
    var allxpenses = MutableLiveData<List<Expenses>>()

    fun validate(): Boolean {
        val isDateValid = TextUtils.isEmpty(date.value)
        val isTimeValid = TextUtils.isEmpty(time.value)
        val isExpensesValid = TextUtils.isEmpty(expenses.value)
        val isAmountValid = Valid.isValidEmail(amount.value)

        if (isDateValid) {
            isValidDate.value = true
            dateErrMsg.value = "Please select any date."
        } else {
            isValidDate.value = false
            dateErrMsg.value = ""
        }
        if (isTimeValid) {
            isValidTime.value = true
            timeErrMsg.value = "Please select any date."
        } else {
            isValidTime.value = false
            timeErrMsg.value = ""
        }
        if (isExpensesValid) {
            isValidExpenses.value = true
            expensesErrMsg.value = "Please enter name."
        } else {
            isValidExpenses.value = false
            expensesErrMsg.value = ""
        }
        if (isAmountValid) {
            isValidAmount.value = true
            amountErrMsg.value = "Input proper email id"
        } else {
            isValidAmount.value = false
            amountErrMsg.value = ""
        }
        return !isDateValid && !isTimeValid && !isExpensesValid && !isAmountValid
    }

    private fun insertEarnings(expenses: Expenses) {
        viewModelScope.launch {
            repository.insertEarnings(expenses)
        }
    }

    fun fetchAllExpenses(): LiveData<List<Expenses>> {
        return repository.getEarnings
    }

    fun fetchAllExpensesByMonth(startDate: Long, endDate: Long): LiveData<List<Expenses>> {
        return repository.getExpensesByMonth(startDate, endDate)
    }

    fun insertAllExpenses() {
        insertEarnings(
            Expenses(
                0,
                convertDateToLong(date.value),
                time.value,
                expenses.value,
                amount.value,
                note.value
            )
        )
    }
}