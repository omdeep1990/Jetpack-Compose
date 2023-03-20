package com.omdeep.jetpackcompose.data.repository

import com.omdeep.jetpackcompose.data.model.PhoneBook
import kotlinx.coroutines.flow.Flow

interface Abstract {
    suspend fun savePhoneBook(phonebook: PhoneBook)
    suspend fun getPhoneBook(): Flow<PhoneBook>
}