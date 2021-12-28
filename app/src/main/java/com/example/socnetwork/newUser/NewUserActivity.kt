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

    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_new)

        nameNewUser = findViewById(R.id.nameInputNew)

    }

    fun saveNewUserClick(view: View) {
//        val newUser = User(2352343246, "dsf", "inna@gmail.com", "читання книги", "two day ago", "https://cdnimg.rg.ru/img/content/177/24/41/1_d_850.jpg", "NNA — румынская певица, исполнительница танцевальной музыки. Инна начала работу с Play & Win в 2008 году.", 245, 1290, 46, 162)

//        val newUser: User? = null
//
//        newUser?.userId = null
//        newUser?.name = nameNewUser.toString()
//        newUser?.email = "inna@gmail.com"
//        newUser?.hobby = "нічого"
//        newUser?.lastOnline = "yesterday"
//        newUser?.photo = "https://pbs.twimg.com/profile_images/2881220369/2b27ac38b43b17a8c5eacfc443ce3384_400x400.jpeg"
//        newUser?.about = "Почётный доктор около 20 ведущих университетов мира, член многих Академий наук"
//        newUser?.following = 245
//        newUser?.followers = 1290
//        newUser?.posts = 46
//        newUser?.likes = 162

//        val newUser: User = User(
//
//        userId = null,
//        name = nameNewUser?.text.toString(),
//        email = "inna@gmail.com",
//        hobby = "нічого",
//        "yesterday",
//        photo = "https://pbs.twimg.com/profile_images/2881220369/2b27ac38b43b17a8c5eacfc443ce3384_400x400.jpeg",
//        about = "Почётный доктор около 20 ведущих университетов мира, член многих Академий наук",
//        following = 245,
//        followers = 1290,
//        posts = 46,
//        likes = 162
//        )
        val application = requireNotNull(this).application

        val dataSource = UserDatabase.getInstance(application).userDatabaseDao

        val viewModelFactory = NewUserViewModelFactory(dataSource, application)


        var newUserViewModel = ViewModelProvider(this, viewModelFactory).get(NewUserViewModel::class.java)

        val newUser: User = User(


            name = nameNewUser?.text.toString(),
            email = "inna@gmail.com",
            hobby = "нічого",
            lastOnline = "yesterday",
            photo = "https://pbs.twimg.com/profile_images/2881220369/2b27ac38b43b17a8c5eacfc443ce3384_400x400.jpeg",
            about = "Почётный доктор около 20 ведущих университетов мира, член многих Академий наук",
            following = 245,
            followers = 1290,
            posts = 46,
            likes = 162
        )


//        if(newUserViewModel.checkStatusUser(newUser)) {
//            newUserViewModel.insert(newUser)
//
//            val intent = Intent(this, AllUserActivity::class.java)
//
//
//        } else {
//            val intent = Intent(this, NewUserActivity::class.java)
//        }
//
//
//
//
//        startActivity(intent)


        newUserViewModel.insert(newUser)

        val intent = Intent(this, AllUserActivity::class.java)

        startActivity(intent)
    }


}