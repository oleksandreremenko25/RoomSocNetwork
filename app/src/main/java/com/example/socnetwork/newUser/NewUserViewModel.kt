package com.example.socnetwork.newUser

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.socnetwork.UserData
import com.example.socnetwork.database.User
import com.example.socnetwork.database.UserDatabaseDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewUserViewModel (val database: UserDatabaseDao, application: Application) : AndroidViewModel(application) {

    fun insert(newUser: User) {
        viewModelScope.launch(Dispatchers.IO) {
            //Перевіряє чи існує в юазі даних такий користувач і якщо його
            // немає то getUserByName повертає null
            if(database.getUserByName(newUser.name) == null) {
                database.insert(newUser)
            }
        }
    }

}