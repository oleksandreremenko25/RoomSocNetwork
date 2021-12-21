package com.example.socnetwork.newUser

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.socnetwork.database.User
import com.example.socnetwork.database.UserDatabaseDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewUserViewModel (val user: User, val database: UserDatabaseDao, application: Application) : AndroidViewModel(application) {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            insert(user)
        }
    }

    private suspend fun insert(newUser: User) {
        database.insert(newUser)
    }

}