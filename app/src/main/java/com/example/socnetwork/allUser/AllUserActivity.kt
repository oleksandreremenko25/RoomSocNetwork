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
import android.content.Intent
import com.example.socnetwork.newUser.NewUserActivity

class AllUserActivity  : AppCompatActivity()  {
    private lateinit var allUserViewModel: AllUserViewModel

    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_all)

        val application = requireNotNull(this).application

        val dataSource = UserDatabase.getInstance(application).userDatabaseDao

        val viewModelFactory = AllUserViewModelFactory(dataSource, application)

        allUserViewModel = ViewModelProvider(this, viewModelFactory).get(AllUserViewModel::class.java)

        observeUsers()
    }



    private fun observeUsers() {
        allUserViewModel.allUserList.observe(this, Observer { users ->
            users?.let {
                showUsers(it)
            }
        })
    }

    private fun showUsers(users: List<User>) {

        var generatedId: Int = 0
        val context: Context = this
        val textBeforeNameUser: String = context.getString(R.string.beforeNameUser);
        val textBeforeLastOnlineUser: String = context.getString(R.string.beforeLastOnlineUser);

        for (oneUser in users) {
            val view: View = layoutInflater.inflate(R.layout.user_short, null)
            val layout = findViewById<LinearLayout>(R.id.users_list_wrapper)

            Picasso.get().load(oneUser.photo).placeholder(R.drawable.no).into(view.photoUser);

            view.idUser.text = oneUser.userId.toString()
            view.nameUser.text = (textBeforeNameUser + " " + oneUser.name)
            view.lastOnlinelUser.text = (textBeforeLastOnlineUser + " " + oneUser.lastOnline)

            view.id = oneUser.userId!!.toInt()
            layout.addView(view)
            generatedId++
        }
    }

    fun userShortClick(views: View) {
        val intent = Intent(this, FullUserActivity::class.java)
        val idUser: Int = views.id
        intent.putExtra("keyUserId", idUser)
        startActivity(intent)
    }

    fun toNewUserClick(views: View) {
        val intent = Intent(this, NewUserActivity::class.java)

        startActivity(intent)
    }
}