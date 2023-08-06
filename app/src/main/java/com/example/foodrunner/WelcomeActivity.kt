package com.example.foodrunner

import android.annotation.SuppressLint
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class WelcomeActivity : AppCompatActivity() {
    lateinit var txtWelcome: TextView
    lateinit var sharedPreferences: SharedPreferences
    var mobile: String? = null
    var password: String? = null

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        sharedPreferences = getSharedPreferences(getString(R.string.shared_preferences),
            MODE_PRIVATE
        )

        txtWelcome = findViewById(R.id.txtWelcome)

            mobile = sharedPreferences.getString("mobile","Failed to load")
            password =sharedPreferences.getString("password","Not available")

            txtWelcome.text = "User $mobile " +
                    "Welcome to our app"


    }
}