package com.example.socnetwork.newUser

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.socnetwork.*
import com.example.socnetwork.UserData
import com.example.socnetwork.allUser.AllUserActivity
import com.example.socnetwork.database.User
import com.example.socnetwork.database.UserDatabase
import com.example.socnetwork.fullUser.FullUserViewModel
import com.example.socnetwork.fullUser.FullUserViewModelFactory

class NewUserActivity  : AppCompatActivity()  {

    var nameNewUser: EditText? = null
    var emailInputNew: EditText? = null
    var hobbyInputNew: EditText? = null
    var photoInputNew: EditText? = null
    var aboutInputNew: EditText? = null

    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_new)

        nameNewUser = findViewById(R.id.nameInputNew)
        emailInputNew = findViewById(R.id.emailInputNew)
        hobbyInputNew = findViewById(R.id.hobbyInputNew)
        photoInputNew = findViewById(R.id.photoInputNew)
        aboutInputNew = findViewById(R.id.aboutInputNew)

    }

    fun saveNewUserClick(view: View) {

        val application = requireNotNull(this).application

        val dataSource = UserDatabase.getInstance(application).userDatabaseDao

        val viewModelFactory = NewUserViewModelFactory(dataSource, application)


        var newUserViewModel = ViewModelProvider(this, viewModelFactory).get(NewUserViewModel::class.java)

        val newUser: User = User(

            name = nameNewUser?.text.toString(),
            email = emailInputNew?.text.toString(),
            hobby = hobbyInputNew?.text.toString(),
            lastOnline = "yesterday",
            photo = photoInputNew?.text.toString(),
            about = aboutInputNew?.text.toString(),
            following = 245,
            followers = 1290,
            posts = 46,
            likes = 162
        )


        newUserViewModel.insert(newUser)

        val intent = Intent(this, AllUserActivity::class.java)

        startActivity(intent)
    }

}