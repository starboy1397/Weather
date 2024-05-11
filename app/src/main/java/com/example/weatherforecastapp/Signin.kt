package com.example.weatherforecastapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.weatherforecastapp.MainActivity
import com.example.weatherforecastapp.R
import com.example.weatherforecastapp.Signup
import com.example.weatherforecastapp.Utilities.MyDatabase
import com.example.weatherforecastapp.Utilities.UserDao
import com.example.weatherforecastapp.databinding.ActivitySigninBinding
import kotlinx.coroutines.launch

class Signin : AppCompatActivity() {
    private lateinit var binding: ActivitySigninBinding
    private lateinit var db: MyDatabase
    private lateinit var userDao: UserDao

    companion object {
        private val TAG = Signin::class.java.simpleName // This is how you define the TAG
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySigninBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d(TAG, "Signin activity started")

        db = Room.databaseBuilder(
            this,
            MyDatabase::class.java,
            "usertable"
        )
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
        userDao = db.getDao()

        binding.btnLogin.setOnClickListener {
            val userName = binding.etUsername.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            Log.d(TAG, "Attempting to log in with username: $userName")

            if (userName.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill in both fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            lifecycleScope.launch {
                if (userDao.isUserValid(userName, password)) {
                    Log.d(TAG, "Login successful for username: $userName")
                    startActivity(Intent(this@Signin, MainActivity::class.java))
                    finish()
                } else {
                    Log.d(TAG, "Login failed for username: $userName")
                    Toast.makeText(this@Signin, "Invalid Username & Password", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.tvGoToSignup.setOnClickListener {
            Log.d(TAG, "Navigating to Signup screen")
            startActivity(Intent(this, Signup::class.java))
            finish()
        }
    }
}
