package com.example.socnetwork.editUser

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.socnetwork.R
import com.example.socnetwork.allUser.AllUserActivity
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.socnetwork.database.User
import com.example.socnetwork.database.UserDatabase
import com.example.socnetwork.fullUser.FullUserViewModel
import com.example.socnetwork.fullUser.FullUserViewModelFactory
import com.squareup.picasso.Picasso
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.user_edit.view.*

class EditUserActivity : AppCompatActivity()   {
    private lateinit var editUserViewModel: EditUserViewModel
    var nameInput: EditText? = null
    var emailInput: EditText? = null
    var hobbyInput: EditText? = null
    var aboutInput: EditText? = null
    var editUser: User? = null

    var idInt: Int? = null

    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_edit)

        val intent = intent

        val userId: Long = intent.getIntExtra("myKey", 0).toLong()

        idInt = userId.toInt()

        val application = requireNotNull(this).application

        val dataSource = UserDatabase.getInstance(application).userDatabaseDao

        val viewModelFactory = EditUserViewModelFactory(userId, dataSource, application)

        editUserViewModel = ViewModelProvider(this, viewModelFactory).get(EditUserViewModel::class.java)

        nameInput = findViewById(R.id.nameInput)
        emailInput = findViewById(R.id.emailInput)
        hobbyInput = findViewById(R.id.hobbyInput)
        aboutInput = findViewById(R.id.aboutInput)

        observeUsers()

    }

    private fun observeUsers() {
        editUserViewModel.user.observe(this, Observer { user ->
            user?.let {
                editUser(it)
                editUser = it
            }
        })
    }

    private fun editUser(oneUser: User) {
        val view: View = layoutInflater.inflate(R.layout.user_edit, null)

        nameInput?.setText(oneUser.name)
        emailInput?.setText(oneUser.email)
        hobbyInput?.setText(oneUser.hobby)
        aboutInput?.setText(oneUser.about)
    }

    fun saveUpdateClick(views: View) {
        val intent = Intent(this, AllUserActivity::class.java)
        editUser!!.name = nameInput!!.getText().toString()
        editUserViewModel.update(editUser!!)
        startActivity(intent)

    }


}