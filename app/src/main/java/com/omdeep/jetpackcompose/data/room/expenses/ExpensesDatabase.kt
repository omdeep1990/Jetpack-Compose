package com.omdeep.jetpackcompose.data.room.expenses

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.omdeep.jetpackcompose.utils.Constants.EXP_DAO

@Database(entities = [Expenses::class], version = 1)
abstract class ExpensesDatabase : RoomDatabase() {
    abstract val dao: ExpensesDao

    companion object {
        @Volatile
        private var INSTANCE: ExpensesDatabase? = null

        fun getInstance(context: Context): ExpensesDatabase {
            var instance = INSTANCE
            synchronized(this) {
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context, ExpensesDatabase::class.java, EXP_DAO).build()
                    INSTANCE = instance
                }
                return instance!!
            }
        }
    }
}