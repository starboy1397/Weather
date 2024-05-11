package com.example.weatherforecastapp.Utilities

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.weatherforecastapp.POJO.UserTable

@Dao
interface UserDao {
    @Insert
    suspend fun insertUser(userTable: UserTable)

    @Query("SELECT EXISTS (SELECT * FROM userTable WHERE userName = :userName)")
    suspend fun isUserTaken(userName: String): Boolean

    @Query("SELECT EXISTS (SELECT * FROM userTable WHERE userName = :userName AND password = :password)")
    suspend fun isUserValid(userName: String, password: String): Boolean

    @Query("SELECT EXISTS (SELECT 1 FROM UserTable WHERE userName = :userName AND password = :password)")
    suspend fun login(userName: String, password: String): Boolean
}