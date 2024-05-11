package com.example.weatherforecastapp.POJO

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "userTable")
class UserTable(
    @field:PrimaryKey(autoGenerate = true) var id: Int,
    var username: String,
    var password: String
)
