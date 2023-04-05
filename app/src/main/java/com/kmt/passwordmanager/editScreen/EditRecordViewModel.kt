package com.kmt.passwordmanager.editScreen

import androidx.lifecycle.ViewModel
import com.kmt.passwordmanager.database.AuthInfoDatabaseDao
import com.kmt.passwordmanager.database.model.AuthInfoRecord

class EditRecordViewModel(private val databaseDao: AuthInfoDatabaseDao) : ViewModel() {
    fun addNewAuthData(data: AuthInfoRecord) {
        databaseDao.insert(data)
    }

    fun editAuthData(data: AuthInfoRecord) {
        databaseDao.update(data)
    }

    fun getRecord(id: Long) : AuthInfoRecord? {
        return databaseDao.getRecordById(id)
    }
}