package com.kmt.passwordmanager.passwordsScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kmt.passwordmanager.database.AuthInfoDatabaseDao
import com.kmt.passwordmanager.database.model.AuthInfoRecord

class PasswordsViewModel(private val databaseDao: AuthInfoDatabaseDao) : ViewModel() {

    // список записей
    private val _records = MutableLiveData<List<AuthInfoRecord>>()
    val records: LiveData<List<AuthInfoRecord>>
        get() = databaseDao.getAllRecords()
}