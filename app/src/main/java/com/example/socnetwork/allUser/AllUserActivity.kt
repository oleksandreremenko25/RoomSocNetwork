package com.example.socnetwork.allUser

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import com.example.socnetwork.*
import com.example.socnetwork.database.UserDatabase
import androidx.lifecycle.ViewModelProvider
import android.content.Context
import android.widget.LinearLayout
import com.example.socnetwork.database.User
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.user_short.view.*
import androidx.lifecycle.Observer

class AllUserActivity  : AppCompatActivity()  {
    private lateinit var allUserViewModel: AllUserViewModel

    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_all)

        // Файл requireNotNullФункція Kotlin створює  IllegalArgumentException якщо значення є null.
        val application = requireNotNull(this).application
        // Посилання на джерело даних через посилання на DAO
        val dataSource = UserDatabase.getInstance(application).userDatabaseDao

        //створення екземпляру viewModelFactory
        val viewModelFactory = AllUserViewModelFactory(dataSource, application)

        // посилання на SleepTrackerViewModel
        allUserViewModel = ViewModelProvider(this, viewModelFactory).get(AllUserViewModel::class.java)

        observeUsers()
    }

    private fun observeUsers() {
        allUserViewModel.allUserList.observe(this, Observer { users ->
            showUsers(users)
        })
    }

    private fun showUsers(users: List<User>) {

        var generatedId: Int = 0
        val context: Context = this
        val textBeforeNameUser: String = context.getString(R.string.beforeNameUser);
        val textBeforeEmailUser: String = context.getString(R.string.beforeEmailUser);

        for (oneUser in users) {
            val view: View = layoutInflater.inflate(R.layout.user_short, null)
            val layout = findViewById<LinearLayout>(R.id.users_list_wrapper)

            Picasso.get().load(oneUser.photo).placeholder(R.drawable.no).into(view.photoUser);

            view.nameUser.text = (textBeforeNameUser + " " + oneUser.name)
            view.lastOnlinelUser.text = (textBeforeEmailUser + " " + oneUser.lastOnline)
            view.id = generatedId;
            layout.addView(view)
            generatedId++
        }
    }


}