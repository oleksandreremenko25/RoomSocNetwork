package com.example.socnetwork.allUser

import android.app.Application
import androidx.lifecycle.*
import com.example.socnetwork.database.UserDatabaseDao
import com.example.socnetwork.database.User
import com.example.socnetwork.UserData
import kotlinx.coroutines.launch
import java.lang.Math.random
import androidx.lifecycle.Observer
import android.content.Context
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AllUserViewModel (val database: UserDatabaseDao, application: Application) : AndroidViewModel(application) {
    private var _allUserList = MutableLiveData<List<User>>()
    var allUserList: LiveData<List<User>> = _allUserList

    init {
        viewModelScope.launch(Dispatchers.IO) {

            // перевірка чи існують якісь записи в базі даних і
            // якщо їх не існує тоді виконується запис в базу даних даниз
            // з локального файлу
            if(database.getAllUser().isEmpty()) {
                for (oneUser in UserData().usersList) {
                    insert(oneUser)
                }
            }

            _allUserList.postValue(getAllUser())

        }
    }

    private suspend fun insert(newUser: User) {

        database.insert(newUser)
    }

    private fun getAllUser(): List<User> {
        return database.getAllUser()
    }
}