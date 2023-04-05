package com.kmt.passwordmanager.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kmt.passwordmanager.database.model.AuthInfoRecord

@Database(entities = [AuthInfoRecord::class], version = 1, exportSchema = false)
abstract class AuthInfoDatabase : RoomDatabase() {

    abstract fun getSleepDatabaseDao(): AuthInfoDatabaseDao

    companion object {
        @Volatile
        private var INSTANCE: AuthInfoDatabase? = null

        fun getInstance(context: Context): AuthInfoDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AuthInfoDatabase::class.java,
                        "authInfo_db"
                    )
                        .allowMainThreadQueries()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}