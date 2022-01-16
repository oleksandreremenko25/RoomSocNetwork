package com.example.socnetwork.fullUser

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.socnetwork.fullUser.FullUserViewModel
import com.example.socnetwork.database.UserDatabaseDao

class FullUserViewModelFactory (var searchIdUser: Long, private val dataSource: UserDatabaseDao, private val application: Application) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FullUserViewModel::class.java)) {
            return FullUserViewModel(searchIdUser, dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}