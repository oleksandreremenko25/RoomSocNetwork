package com.example.socnetwork.editUser

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.socnetwork.R
import com.example.socnetwork.allUser.AllUserActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.socnetwork.database.User
import com.example.socnetwork.database.UserDatabase
import com.example.socnetwork.fullUser.FullUserViewModel
import com.example.socnetwork.fullUser.FullUserViewModelFactory
import com.squareup.picasso.Picasso
import androidx.lifecycle.Observer
import com.example.socnetwork.fullUser.FullUserActivity
import kotlinx.android.synthetic.main.user_edit.view.*

class EditUserActivity : AppCompatActivity()   {
    private lateinit var editUserViewModel: EditUserViewModel
    var nameInput: EditText? = null
    var emailInput: EditText? = null
    var hobbyInput: EditText? = null
    var photoInput: EditText? = null
    var aboutInput: EditText? = null
    var editUser: User? = null
    var idInt: Int? = null
    var buttonSaveNewUser: Button? = null

    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_edit)

        val intent = intent

        val userId: Long = intent.getIntExtra("keyUserId", 0).toLong()

        idInt = userId.toInt()

        val application = requireNotNull(this).application

        val dataSource = UserDatabase.getInstance(application).userDatabaseDao

        val viewModelFactory = EditUserViewModelFactory(dataSource, application)

        editUserViewModel = ViewModelProvider(this, viewModelFactory).get(EditUserViewModel::class.java)

        nameInput = findViewById(R.id.nameInput)
        emailInput = findViewById(R.id.emailInput)
        hobbyInput = findViewById(R.id.hobbyInput)
        photoInput = findViewById(R.id.photoInput)
        aboutInput = findViewById(R.id.aboutInput)
        buttonSaveNewUser = findViewById(R.id.buttonSaveNewUser)

        editUserViewModel.setUserId(userId)
        observeUsers()

        buttonSaveNewUser?.setOnClickListener {
            saveUpdateClick()
        }
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
        nameInput?.setText(oneUser.name)
        emailInput?.setText(oneUser.email)
        hobbyInput?.setText(oneUser.hobby)
        photoInput?.setText(oneUser.photo)
        aboutInput?.setText(oneUser.about)
    }

    fun saveUpdateClick() {
        val intent = Intent(this, FullUserActivity::class.java)
        editUser!!.name = nameInput!!.text.toString()
        editUser!!.email = emailInput!!.text.toString()
        editUser!!.hobby = hobbyInput!!.text.toString()
        editUser!!.photo = photoInput!!.text.toString()
        editUser!!.about = aboutInput!!.text.toString()
        editUserViewModel.update(editUser!!)
        val idUser: Int = editUser!!.userId!!.toInt()
        intent.putExtra("keyUserId", idUser)
        startActivity(intent)
    }

}