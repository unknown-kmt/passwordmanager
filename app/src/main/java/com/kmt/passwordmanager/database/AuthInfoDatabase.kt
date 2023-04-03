/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
                    ).build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}