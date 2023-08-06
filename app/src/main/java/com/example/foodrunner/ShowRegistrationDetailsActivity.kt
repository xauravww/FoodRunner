package com.example.foodrunner

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ShowRegistrationDetailsActivity : AppCompatActivity() {
    lateinit var txtName:TextView
    lateinit var txtEmail:TextView
    lateinit var txtMobile:TextView
    lateinit var txtDelivery:TextView
    lateinit var txtPassword:TextView
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_registration_details)
        txtName = findViewById(R.id.txtName)
        txtEmail = findViewById(R.id.txtEmail)
        txtMobile = findViewById(R.id.txtMobile)
        txtDelivery = findViewById(R.id.txtDelivery)
        txtPassword = findViewById(R.id.txtPassword)



        if(intent!=null){
            val name  = intent.getStringExtra("name")
            val email =  intent.getStringExtra("email")
            val mobile =  intent.getStringExtra("mobile")
            val delivery =  intent.getStringExtra("delivery")
            val pass =  intent.getStringExtra("pass")

            txtName.text = "Name  : $name"
            txtEmail.text = "Email  : $email"
            txtMobile.text = "Mobile : $mobile"
            txtDelivery.text = "Delivery : $delivery"
            txtPassword.text = "Password : $pass"

        }
    }
}