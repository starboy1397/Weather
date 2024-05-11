package com.example.weatherforecastapp.Utilities

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.weatherforecastapp.POJO.UserTable

@Database(entities = [UserTable::class], version = 1)
abstract class MyDatabase : RoomDatabase() {
    abstract fun getDao(): UserDao
}