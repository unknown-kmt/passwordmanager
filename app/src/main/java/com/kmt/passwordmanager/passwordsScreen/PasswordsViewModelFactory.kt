package com.kmt.passwordmanager.passwordsScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kmt.passwordmanager.database.AuthInfoDatabaseDao

class PasswordsViewModelFactory(private val dao: AuthInfoDatabaseDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PasswordsViewModel::class.java)) {
            return PasswordsViewModel(dao) as T
        }

        throw java.lang.IllegalArgumentException("Unknown ViewModel class")
    }
}