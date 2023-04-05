package com.kmt.passwordmanager.editScreen

import androidx.lifecycle.ViewModel
import com.kmt.passwordmanager.database.AuthInfoDatabaseDao
import com.kmt.passwordmanager.database.model.AuthInfoRecord

class EditRecordViewModel(private val record: AuthInfoRecord,
                          private val databaseDao: AuthInfoDatabaseDao) : ViewModel() {
    // TODO: Implement the ViewModel
}