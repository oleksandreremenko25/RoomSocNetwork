package com.example.socnetwork.editUser

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.socnetwork.database.User
import com.example.socnetwork.database.UserDatabaseDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditUserViewModel (var searchIdUser: Long, val database: UserDatabaseDao, application: Application) : AndroidViewModel(application) {
    private var _user = MutableLiveData<User>()
    val user: LiveData<User> = _user

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _user.postValue(getUser(searchIdUser))
        }
    }

    suspend fun getUser(id: Long): User? {
        return database.getUser(id)
    }

    fun update (updateUser: User) {
        viewModelScope.launch(Dispatchers.IO) {
            database.update(updateUser)
        }
    }
}
