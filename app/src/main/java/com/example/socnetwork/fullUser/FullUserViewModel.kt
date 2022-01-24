package com.example.socnetwork.fullUser

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.socnetwork.database.User
import com.example.socnetwork.database.UserDatabaseDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FullUserViewModel (val database: UserDatabaseDao, application: Application) : AndroidViewModel(application) {
    private var _user = MutableLiveData<User>()
    val user: LiveData<User> = _user
    private var locIdUser: Long? = null

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _user.postValue(getUser(locIdUser!!))
        }
    }

    fun setUserId(searchIdUser: Long) {
        locIdUser = searchIdUser
    }

    private suspend fun getUser(id: Long): User? {
        return database.getUser(id)
    }
}