package com.example.socnetwork.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user_table")
data class User(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "userId")
    var userId: Long? = null,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "email")
    var email: String,

    @ColumnInfo(name = "hobby")
    var hobby: String,

    @ColumnInfo(name = "last_online")
    var lastOnline: String,

    @ColumnInfo(name = "photo")
    var photo: String,

    @ColumnInfo(name = "about")
    var about: String,

    @ColumnInfo(name = "following")
    var following: Int,

    @ColumnInfo(name = "followers")
    var followers: Int,

    @ColumnInfo(name = "posts")
    var posts: Int,

    @ColumnInfo(name = "likes")
    var likes: Int) {

    init {
        createPhoto()
    }

    fun createPhoto() {
        if (photo.length == 0) {
            photo = "no"
        }
    }
}