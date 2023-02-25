package com.omdeep.jetpackcompose.data.room

import android.content.Context
import androidx.room.*
import com.omdeep.jetpackcompose.utils.Constants.ROOM_DATABASE

@Database(entities = [User::class], version = 1)
abstract class UserDatabase : RoomDatabase() {

    abstract val dao: UserDao

    companion object {
        @Volatile
        private var INSTANCE: UserDatabase? = null

        fun getInstance(context: Context): UserDatabase {
            var instance = INSTANCE
            synchronized(this) {
                if (instance == null) {
                    instance =
                        Room.databaseBuilder(context, UserDatabase::class.java, ROOM_DATABASE)
                            .build()
                    INSTANCE = instance
                }
                return instance!!
            }
        }
    }
}