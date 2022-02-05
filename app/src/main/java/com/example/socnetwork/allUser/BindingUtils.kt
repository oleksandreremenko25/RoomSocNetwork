package com.example.socnetwork.allUser

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.socnetwork.R
import com.example.socnetwork.database.User
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.user_short.view.*



    // Ця функція буде вашим адаптером для обчислення та форматування тривалості сну
    @BindingAdapter("userImage")
    fun ImageView.setUserImage(oneUser: User) {

        Picasso.get().load(oneUser.photo).placeholder(R.drawable.no).into(this)

    }

