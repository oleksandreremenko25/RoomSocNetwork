package com.example.socnetwork.newUser

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.socnetwork.database.User
import com.example.socnetwork.database.UserDatabaseDao

class NewUserViewModelFactory (val user: User, private val dataSource: UserDatabaseDao, private val application: Application) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(NewUserViewModel::class.java)) {
            return NewUserViewModel(user, dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}