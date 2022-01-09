package com.example.socnetwork.fullUser

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Observer
import com.example.socnetwork.R
import com.example.socnetwork.allUser.AllUserViewModel
import com.example.socnetwork.allUser.AllUserViewModelFactory
import com.example.socnetwork.database.User
import com.example.socnetwork.database.UserDatabase
import com.example.socnetwork.editUser.EditUserActivity

import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.user_full.view.*

class FullUserActivity : AppCompatActivity()  {
    private lateinit var fullUserViewModel: FullUserViewModel
    var nameUser: TextView? = null
    var lastOnlineUser: TextView? = null
    var emailUser: TextView? = null
    var photoUser: ImageView? = null
    var aboutUser: TextView? = null
    var numberFollowing: TextView? = null
    var numberFollowers: TextView? = null
    var numberPosts: TextView? = null
    var numberLikes: TextView? = null
    var authorPostsIcon: ImageView? = null
    var authorPostsName: TextView? = null
    var authorPostsLastOnline: TextView? = null
    var idUser: TextView? = null


    var idInt: Int? = null

    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_full)

        val intent = intent
        val userId: Long = intent.getIntExtra("myKey", 0).toLong()
        idInt = userId.toInt()
        val application = requireNotNull(this).application

        val dataSource = UserDatabase.getInstance(application).userDatabaseDao

        val viewModelFactory = FullUserViewModelFactory(userId, dataSource, application)

        fullUserViewModel = ViewModelProvider(this, viewModelFactory).get(FullUserViewModel::class.java)

        photoUser = findViewById(R.id.photoUser)
        nameUser = findViewById(R.id.nameUser)
        aboutUser = findViewById(R.id.aboutUser)
        numberFollowing = findViewById(R.id.numberFollowing)
        numberFollowers = findViewById(R.id.numberFollowers)
        numberPosts = findViewById(R.id.numberPosts)
        numberLikes = findViewById(R.id.numberLikes)
        authorPostsIcon = findViewById(R.id.authorPostsIcon)
        authorPostsName = findViewById(R.id.authorPostsName)
        authorPostsLastOnline = findViewById(R.id.authorPostsLastOnline)
        idUser = findViewById(R.id.idUser)


        observeUsers()
    }

    private fun observeUsers() {
        fullUserViewModel.user.observe(this, Observer { user ->
            user?.let {
                showUser(it)
            }
        })
    }

    private fun showUser(oneUser: User) {
        val view: View = layoutInflater.inflate(R.layout.user_full, null)

        Picasso.get().load(oneUser.photo).placeholder(R.drawable.no).into(photoUser);

        nameUser?.text = oneUser.name
        lastOnlineUser?.text = oneUser.lastOnline
        emailUser?.text = oneUser.email
        aboutUser?.text = oneUser.about
        numberFollowing?.text = oneUser.following.toString()
        numberFollowers?.text = oneUser.followers.toString()
        idUser?.text = oneUser.userId.toString()
        idInt = oneUser.userId!!.toInt()

        val textForNumberPosts: String = getString(R.string.tittlePosts, oneUser.posts.toString())
        numberPosts?.text = textForNumberPosts

        val textForNumberLikes: String = getString(R.string.tittlePosts, oneUser.likes.toString())
        numberLikes?.text = textForNumberLikes

        Picasso.get().load(oneUser.photo).placeholder(R.drawable.no).into(authorPostsIcon);
        authorPostsName?.text = oneUser.name
        authorPostsLastOnline?.text = oneUser.lastOnline
    }

    fun userEditClick (views: View) {
        val intent = Intent(this, EditUserActivity::class.java)

        intent.putExtra("myKey", idInt)
        startActivity(intent)
    }
}
