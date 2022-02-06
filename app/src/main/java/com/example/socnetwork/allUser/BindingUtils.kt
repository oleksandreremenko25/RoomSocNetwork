package com.example.socnetwork.allUser

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.socnetwork.R
import com.example.socnetwork.database.User
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.user_short.view.*

// Ця функція є адаптером для відображення фотографії юзера в user_short.xml
@BindingAdapter("userImage")
fun ImageView.setUserImage(oneUser: User) {
    Picasso.get().load(oneUser.photo).placeholder(R.drawable.no).into(this)
}

// Ця функція є адаптером для перетворення типу Long в тип Int - id номера юзера в user_short.xml
@BindingAdapter("idStringUser")
fun TextView.setIdString(onewUser: User) {
    text = onewUser.userId.toString()
}

