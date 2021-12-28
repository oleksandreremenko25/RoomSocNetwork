package com.example.socnetwork.newUser

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.socnetwork.database.User
import com.example.socnetwork.database.UserDatabaseDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewUserViewModel (val database: UserDatabaseDao, application: Application) : AndroidViewModel(application) {

    fun insert(newUser: User) {
        viewModelScope.launch(Dispatchers.IO) {
            database.insert(newUser)
        }
    }

//    fun checkStatusUser(newUser: User): Boolean {
//        var statusUser: Boolean? = null
//        viewModelScope.launch(Dispatchers.IO) {
//            if (database.check(newUser).about.length > 0) {
//                statusUser = true
//            } else {
//                statusUser = false
//            }
//
//        }
//        return statusUser!!
//    }

}