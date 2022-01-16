package com.example.socnetwork.allUser

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.socnetwork.database.UserDatabaseDao
import com.example.socnetwork.allUser.AllUserViewModel

class AllUserViewModelFactory(private val dataSource: UserDatabaseDao, private val application: Application) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    // код перевизначає create(), який приймає будь-який тип класу як аргумент і повертає a ViewModel.
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        // В тілі о create(), код перевіряє наявність AllUserViewModel клас доступний,
        // і якщо є, повертає його екземпляр. В іншому випадку код видає виняток.
        // (здебільшого це шаблонний код, тому ви можете повторно використовувати код для майбутніх фабрик моделей перегляду. )
        if (modelClass.isAssignableFrom(AllUserViewModel::class.java)) {
            return AllUserViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
