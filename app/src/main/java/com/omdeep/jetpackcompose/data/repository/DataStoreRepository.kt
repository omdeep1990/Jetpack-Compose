package com.omdeep.jetpackcompose.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import com.omdeep.jetpackcompose.utils.Constants.Pref_DataStore_NAME
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.omdeep.jetpackcompose.data.model.PhoneBook
import com.omdeep.jetpackcompose.utils.Constants.NAME
import com.omdeep.jetpackcompose.utils.Constants.PHONE_NUMBER
import com.omdeep.jetpackcompose.utils.Constants.address
import kotlinx.coroutines.flow.map

val Context.datastore : DataStore<Preferences> by  preferencesDataStore(name = Pref_DataStore_NAME)

class DataStoreRepository(private val context: Context) : Abstract{

    override suspend fun savePhoneBook(phonebook: PhoneBook) {
        context.datastore.edit { phonebooks->
            phonebooks[NAME] = phonebook.name
            phonebooks[PHONE_NUMBER]= phonebook.phone
            phonebooks[address]= phonebook.address

        }
    }

    override suspend fun getPhoneBook() = context.datastore.data.map { phonebook ->
        PhoneBook(
            name = phonebook[NAME]!!,
            address =  phonebook[address]!!,
            phone = phonebook[PHONE_NUMBER]!!
        )
    }

}