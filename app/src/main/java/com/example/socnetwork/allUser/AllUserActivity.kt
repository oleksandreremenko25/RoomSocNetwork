package com.example.socnetwork.allUser

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import com.example.socnetwork.*
import com.example.socnetwork.database.UserDatabase
import androidx.lifecycle.ViewModelProvider
import android.content.Context
import android.util.Log
import android.widget.LinearLayout
import com.example.socnetwork.database.User
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.user_short.view.*
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.socnetwork.fullUser.FullUserActivity
import com.example.socnetwork.editUser.EditUserActivity
import android.content.Intent
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.socnetwork.newUser.NewUserActivity

class AllUserActivity  : AppCompatActivity()  {
    private lateinit var allUserViewModel: AllUserViewModel
    var allSleep: RecyclerView? = null
    var idUser: TextView? = null

        @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_all)

        val application = requireNotNull(this).application

        val dataSource = UserDatabase.getInstance(application).userDatabaseDao

        val viewModelFactory = AllUserViewModelFactory(dataSource, application)

        allUserViewModel = ViewModelProvider(this, viewModelFactory).get(AllUserViewModel::class.java)

        // Файлу RecyclerView потрібно знати який адаптер використовувати,
        // щоб отримати утримувачі перегляду.
        val adapter = AllUserAdapter()

        // Створіть спостерігача для allUserList змінної.
        allUserViewModel.allUserList.observe(this, Observer { users ->
            // Усередині спостерігача, коли отримується ненульове значення (users),
            // призначте значення адаптеру submitList(it). Це завершений код для спостерігача
            // та налаштування даних: Ваш код повинен повідомляти ListAdapter коли буде
            // доступний змінений список. ListAdapter надає метод під назвою submitList() розповісти
            // ListAdapter що нова версія списку доступна. Коли цей метод викликається, то ListAdapter
            // відрізняє новий список від старого та виявляє елементи, які були додані, видалені,
            // переміщені чи змінені. Потім ListAdapter оновлює елементи, які показує RecyclerView.
            users?.let {
                adapter.submitList(it)
            }
        })

        // Отримання посилання на обєк звязування (RecyclerView в який
        // потім будуть вкидуватись макети user_short)
        allSleep = findViewById(R.id.all_sleep)

        // Після того як отримали посилання на об’єкт зв’язування, зв’язуєм RecyclerView з adapter
        allSleep?.adapter = adapter





    }

    fun userShortClick(views: View) {

        val intent = Intent(this, FullUserActivity::class.java)



        // зчитуєм id номер user для його передачі в FullUserActivity
//        val idUser: Int = views.id
        val idUser: Int = views.id


        intent.putExtra("keyUserId", idUser)

        startActivity(intent)
    }










    fun toNewUserClick(views: View) {
        val intent = Intent(this, NewUserActivity::class.java)
        startActivity(intent)
    }
}