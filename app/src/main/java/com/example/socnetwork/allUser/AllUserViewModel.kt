package com.example.socnetwork.allUser

import android.app.Application
import androidx.lifecycle.*
import com.example.socnetwork.database.UserDatabaseDao
import com.example.socnetwork.database.User
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

            insert(User(name = "Sasha", email = "sasha@gmail.com", hobby = "нічого", lastOnline = "yesterday", photo = "https://pbs.twimg.com/profile_images/2881220369/2b27ac38b43b17a8c5eacfc443ce3384_400x400.jpeg", about = "Почётный доктор около 20 ведущих университетов мира, член многих Академий наук", following = 245, followers = 1290, posts = 46, likes = 162))
            insert(User(name = "Inna", email = "inna@gmail.com", hobby = "нічого", lastOnline = "yesterday", photo = "https://pbs.twimg.com/profile_images/2881220369/2b27ac38b43b17a8c5eacfc443ce3384_400x400.jpeg", about = "Почётный доктор около 20 ведущих университетов мира, член многих Академий наук", following = 245, followers = 1290, posts = 46, likes = 162))

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