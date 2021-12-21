package com.example.socnetwork.fullUser

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.socnetwork.fullUser.FullUserViewModel
import com.example.socnetwork.database.UserDatabaseDao

class FullUserViewModelFactory (var searchIdUser: Long, private val dataSource: UserDatabaseDao, private val application: Application) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    // код перевизначає create(), який приймає будь-який тип класу як аргумент і повертає a ViewModel.
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        // В тілі о create(), код перевіряє наявність SleepTrackerViewModelклас доступний,
        // і якщо є, повертає його екземпляр. В іншому випадку код видає виняток.
        // (здебільшого це шаблонний код, тому ви можете повторно використовувати код для майбутніх фабрик моделей перегляду. )
        if (modelClass.isAssignableFrom(FullUserViewModel::class.java)) {
            return FullUserViewModel(searchIdUser, dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}