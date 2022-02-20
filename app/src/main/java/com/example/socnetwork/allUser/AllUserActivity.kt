package com.example.socnetwork.allUser

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import com.example.socnetwork.*
import com.example.socnetwork.database.UserDatabase
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Observer
import com.example.socnetwork.fullUser.FullUserActivity
import android.content.Intent
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.RecyclerView
import com.example.socnetwork.newUser.NewUserActivity

class AllUserActivity  : AppCompatActivity()  {
    private lateinit var allUserViewModel: AllUserViewModel
    var allSleep: RecyclerView? = null
    private var launcherForNewUserActivity: ActivityResultLauncher<Intent>? = null
    var messageStatusLostOperation: TextView? = null

    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_all)
        messageStatusLostOperation = findViewById(R.id.messageStatusLostOperation)
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


        // Запускається тоді коли приходить відповідь Activity яке з його допомогою його запустили
        launcherForNewUserActivity = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            // змінна result несе в собі код
            result: ActivityResult ->
            if(result.resultCode == RESULT_OK) {
                // якщо прийшов результат і він ніc існформацію під назвою
                // nameUserFromNewUserActivity то він буде записаний в змінну name
                val name = result.data?.getStringExtra("nameUserFromNewUserActivity")
                if (name!!.isNotEmpty()) {
                    messageStatusLostOperation?.text = "Анкету " + name + " збережено"
                }

            }
        }

    }

    fun userShortClick(views: View) {
        val intent = Intent(this, FullUserActivity::class.java)
        // зчитуєм id номер user для його передачі в FullUserActivity
        val idUser: Int = views.id
        intent.putExtra("keyUserId", idUser)
        startActivity(intent)

    }

    fun toNewUserClick(views: View) {
        val intent = Intent(this, NewUserActivity::class.java)
//        startActivity(intent)
        launcherForNewUserActivity?.launch(intent)

    }
}