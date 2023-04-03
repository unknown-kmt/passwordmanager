package com.kmt.passwordmanager.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.kmt.passwordmanager.database.model.AuthInfoRecord

@Dao
interface AuthInfoDatabaseDao {

    @Insert
    fun insert(authInfoRecord: AuthInfoRecord)

    @Update
    fun update(authInfoRecord: AuthInfoRecord)

    @Delete
    fun delete(authInfoRecord: AuthInfoRecord)

    @Query(value = "SELECT * FROM auth_info_table WHERE id = :id")
    fun getRecordById(id: Long): AuthInfoRecord?

    @Query(value = "SELECT * FROM auth_info_table ORDER BY id DESC")
    fun getAllRecords(): LiveData<List<AuthInfoRecord>>
}