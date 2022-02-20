package com.example.socnetwork.allUser

import android.app.Application
import androidx.lifecycle.*
import com.example.socnetwork.database.UserDatabaseDao
import com.example.socnetwork.database.User
import com.example.socnetwork.UserData
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers

class AllUserViewModel (val database: UserDatabaseDao, application: Application) : AndroidViewModel(application) {
    private var _allUserList = MutableLiveData<List<User>>()
//    var allUserList: LiveData<List<User>> = _allUserList
    val allUserList: LiveData<List<User>> = database.getAllUser()

    init {
    }

    private suspend fun insert(newUser: User) {
        database.insert(newUser)
    }

    // Перевірка чи існуюсь записи в базі даних
    fun populate() = viewModelScope.launch {(Dispatchers.IO)
        //Бере перший запис в базі даних
        val users = database.getFirstUser()
        // Перевіряє чи те що було взяте в першій строкі не null і не пусте
        if(users == null) {
            for (oneUser in UserData.usersList) {
                insert(oneUser)
            }
        }
    }
}