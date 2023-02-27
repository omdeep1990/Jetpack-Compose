package com.omdeep.jetpackcompose.data.room.earnings

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.omdeep.jetpackcompose.utils.Constants.EAR_DB

@Database(entities = [Earnings::class], version = 1)
abstract class EarningsDatabase : RoomDatabase() {
    abstract val dao: EarningsDao

    companion object {
        @Volatile
        private var INSTANCE: EarningsDatabase? = null

        fun getInstance(context: Context): EarningsDatabase {
            var instance = INSTANCE
            synchronized(this) {
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context, EarningsDatabase::class.java, EAR_DB).build()
                    INSTANCE = instance
                }
                return instance!!
            }
        }
    }
}