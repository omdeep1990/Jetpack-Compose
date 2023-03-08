package com.omdeep.jetpackcompose.data.room

import android.content.Context
import androidx.room.*
import com.omdeep.jetpackcompose.data.room.tables.Earnings
import com.omdeep.jetpackcompose.data.room.tables.Expenses
import com.omdeep.jetpackcompose.data.room.tables.User
import com.omdeep.jetpackcompose.utils.Constants.ROOM_DATABASE
import com.omdeep.jetpackcompose.utils.Constants.VERSION

@Database(entities = [User::class, Earnings::class, Expenses::class], version = VERSION)
abstract class MainDatabase : RoomDatabase() {
    abstract val dao: MainDao
    companion object {
        @Volatile
        private var INSTANCE: MainDatabase? = null

        fun getInstance(context: Context): MainDatabase {
            var instance = INSTANCE
            synchronized(this) {
                if (instance == null) {
                    instance =
                        Room.databaseBuilder(context, MainDatabase::class.java, ROOM_DATABASE)
                            .build()
                    INSTANCE = instance
                }
                return instance!!
            }
        }
    }
}