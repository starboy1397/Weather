package com.example.weatherforecastapp

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.weatherforecastapp.MainActivity
import com.example.weatherforecastapp.POJO.UserTable
import com.example.weatherforecastapp.R
import com.example.weatherforecastapp.Utilities.MyDatabase
import com.example.weatherforecastapp.Utilities.UserDao
import com.example.weatherforecastapp.databinding.ActivitySignupBinding
import kotlinx.coroutines.launch

class Signup : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private lateinit var db: MyDatabase
    private lateinit var userDao: UserDao

    companion object {
        var isAllowed = false
        private val TAG = Signup::class.java.simpleName // This is how you define the TAG
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d(TAG, "Signup activity started")

        db = Room.databaseBuilder(
            this, MyDatabase::class.java, "usertable"
        )
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
        userDao = db.getDao()

        binding.etUsername.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(editable: Editable?) {
                val userName = editable.toString().trim()
                lifecycleScope.launch {
                    if (userDao.isUserTaken(userName)) {
                        isAllowed = false
                        Log.d(TAG, "Username $userName is already taken")
                        Toast.makeText(this@Signup, "Username Already Taken", Toast.LENGTH_SHORT).show()
                    } else {
                        isAllowed = true
                        Log.d(TAG, "Username $userName is available")
                    }
                }
            }
        })

        binding.btnSignUp.setOnClickListener {
            val username = binding.etUsername.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            Log.d(TAG, "Attempting to sign up with username: $username")

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            } else if (isAllowed) {
                lifecycleScope.launch {
                    val userTable = UserTable(0, username, password)
                    userDao.insertUser(userTable)
                    Log.d(TAG, "User $username registered successfully")
                    Toast.makeText(this@Signup, "User registered successfully!", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@Signup, Signin::class.java))
                    finish()
                }
            } else {
                Toast.makeText(this, "Username Already Taken", Toast.LENGTH_SHORT).show()
            }
        }

        binding.tvGoToLogin.setOnClickListener {
            Log.d(TAG, "Navigating to Signin screen")
            startActivity(Intent(this, Signin::class.java))
        }
    }
}
