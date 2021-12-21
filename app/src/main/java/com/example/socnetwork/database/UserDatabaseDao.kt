package com.example.socnetwork.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.lifecycle.LiveData

@Dao
interface UserDatabaseDao {

    @Insert
    suspend fun insert(user: User)

    @Update
    suspend fun update(user: User)

    @Query("SELECT * from user_table WHERE userId = :key")
    suspend fun get(key: Long): User?

    @Query("DELETE FROM user_table")
    suspend fun clear()

    @Query("SELECT * FROM user_table WHERE userId=(:userId)")
    suspend fun getUser(userId: Long): User?

    @Query("SELECT * FROM user_table ORDER BY userId DESC")

    fun getAllUser(): List<User>
}