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
    private lateinit var newUserViewModel: NewUserViewModel
    var newUser: User? = null
    var nameNewUser: EditText? = null

    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_new)

        nameNewUser = findViewById(R.id.nameInputNew)

    }

    fun saveNewUserClick(view: View) {

        newUser?.name = nameNewUser.toString()
        newUser?.email = "inna@gmail.com"
        newUser?.hobby = "нічого"
        newUser?.lastOnline = "yesterday"
        newUser?.photo = "https://pbs.twimg.com/profile_images/2881220369/2b27ac38b43b17a8c5eacfc443ce3384_400x400.jpeg"
        newUser?.about = "Почётный доктор около 20 ведущих университетов мира, член многих Академий наук"
        newUser?.following = 245
        newUser?.followers = 1290
        newUser?.posts = 46
        newUser?.likes = 162

        val application = requireNotNull(this).application

        val dataSource = UserDatabase.getInstance(application).userDatabaseDao

        NewUserViewModelFactory(newUser!!, dataSource, application)

    }


}