package com.kmt.passwordmanager.editScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kmt.passwordmanager.database.AuthInfoDatabaseDao
import com.kmt.passwordmanager.database.model.AuthInfoRecord

class EditRecordViewModelFactory(private val dao: AuthInfoDatabaseDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EditRecordViewModel::class.java)) {
            return EditRecordViewModel(dao) as T
        }

        throw java.lang.IllegalArgumentException("Unknown ViewModel class")
    }
}