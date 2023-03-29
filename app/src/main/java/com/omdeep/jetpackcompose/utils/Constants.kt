package com.omdeep.jetpackcompose.utils

import androidx.datastore.preferences.core.stringPreferencesKey

object Constants {
    const val HOME = "Home"
    const val PROFILE = "Profile"
    const val VIDEO = "Video"
    const val YOGA = "Yoga"
    const val ABOUT = "About"
    const val home = "home"
    const val profile = "profile"
    const val video = "video"
    const val yoga = "yoga"
    const val about = "about"

    const val CHATS = "chats"
    const val MESSAGES = "messages"
    const val UID = "uid"
    const val IMAGE_PATH = "image_path"
    const val JPG_EXT = ".jpg"

    const val ROOM_DATABASE = "room_database"
    const val VERSION = 1
    const val PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%^&+=])(?=\\S+\$).{8,}\$"

    //NavController paths: -
    const val LEDGER = "my_ledger"
    const val EARNINGS = "earnings"
    const val EXPENSES = "expenses"
    const val GET_REPORT = "get_report"
    const val EARNINGS_REPORT = "earnings_report"
    const val EXPENSES_REPORT = "expenses_report"
    /*DataStore paths*/
    const val PREF_DATA_STORE = "pref_data_store"
    const val PROTO_DATA_STORE = "proto_data_store"


    const val DATE_PATTERN = "MM/yyyy"
    val monthList = listOf("January", "February", "March", "April", "May", "June", "July", "August",
        "September", "October", "November", "December")
    val yearList = listOf("2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029",
        "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040")

    //Preferences data store objects: -
    const val Pref_DataStore_NAME = "PHONEBOOK"
    val NAME = stringPreferencesKey("NAME")
    val PHONE_NUMBER = stringPreferencesKey("PHONE")
    val address = stringPreferencesKey("ADDRESS")

    const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"


}