package com.example.socnetwork.allUser

import android.app.Application
import androidx.lifecycle.*
import com.example.socnetwork.database.UserDatabaseDao
import com.example.socnetwork.database.User
import kotlinx.coroutines.launch
import java.lang.Math.random

class AllUserViewModel (val database: UserDatabaseDao, application: Application) : AndroidViewModel(application) {
    private var _allUserList = MutableLiveData<List<User>>()
    var allUserList: LiveData<List<User>> = _allUserList

    init {

        viewModelScope.launch {

            insert(User(getRandomId(), "Sasha", "sasha@gmail.com", "нічого", "yesterday", "https://pbs.twimg.com/profile_images/2881220369/2b27ac38b43b17a8c5eacfc443ce3384_400x400.jpeg", "Почётный доктор около 20 ведущих университетов мира, член многих Академий наук", 245, 1290, 46, 162))
            insert(User(getRandomId(),"Inna", "inna@gmail.com", "нічого", "yesterday", "https://pbs.twimg.com/profile_images/2881220369/2b27ac38b43b17a8c5eacfc443ce3384_400x400.jpeg", "Почётный доктор около 20 ведущих университетов мира, член многих Академий наук", 245, 1290, 46, 162))

            _allUserList.value = getAllUser().value
        }


    }

    private fun getRandomId(): Long {
        return (0 until 10).random().toLong()
    }

    private suspend fun insert(newUser: User) {
        database.insert(newUser)
    }

    private fun getAllUser(): LiveData<List<User>> {
        return database.getAllUser()
    }

}