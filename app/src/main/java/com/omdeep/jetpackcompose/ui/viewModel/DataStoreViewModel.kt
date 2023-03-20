package com.omdeep.jetpackcompose.ui.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omdeep.jetpackcompose.data.model.PhoneBook
import com.omdeep.jetpackcompose.data.repository.DataStoreRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DataStoreViewModel(private  val repository: DataStoreRepository) : ViewModel() {
    var phone : MutableState<String> = mutableStateOf("")
    var address : MutableState<String> = mutableStateOf("")
    var name : MutableState<String> = mutableStateOf("")

    private var phonebook : MutableLiveData<PhoneBook> = MutableLiveData()

    init {
        retrieveData()
    }

    fun saveData(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.savePhoneBook(
                PhoneBook(
                    phone = phone.value,
                    address = address.value,
                    name = name.value
                )
            )
        }
    }

    private fun retrieveData() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getPhoneBook().collect {

                withContext(Dispatchers.Main) {
                    phonebook.value = it
                }
            }
        }
    }

}