package com.example.socnetwork.editUser

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.socnetwork.database.UserDatabaseDao
import com.example.socnetwork.editUser.EditUserViewModel

class EditUserViewModelFactory (private val dataSource: UserDatabaseDao, private val application: Application) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EditUserViewModel::class.java)) {
            return EditUserViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}