package com.example.foodrunner

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.TextView

class ShowForgotDetailsActivity : AppCompatActivity() {
    lateinit var txtEmail:TextView
    lateinit var txtMobile:TextView
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_forgot_details)
        txtEmail = findViewById(R.id.txtEmail)
        txtMobile = findViewById(R.id.txtMobile)
        if(intent!= null){
            val email = intent.getStringExtra("email")
            val mobile = intent.getStringExtra("mobile")

            txtEmail.text = "Email : $email"
            txtMobile.text = "Mobile  : $mobile"
        }
    }
}