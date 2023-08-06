package com.example.foodrunner

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.ComponentActivity

class LoginActivity : ComponentActivity() {
    lateinit var sharedPreferences:SharedPreferences

    lateinit var edtMobile: EditText
    lateinit var edtPass: EditText
    lateinit var btnLogin: Button
    lateinit var txtSignUp:TextView
    lateinit var txtForgot:TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        edtMobile = findViewById(R.id.edtMobile)
        edtPass = findViewById(R.id.edtPass)
        btnLogin = findViewById(R.id.btnLogin)
        txtSignUp = findViewById(R.id.txtSignUp)
        txtForgot =findViewById(R.id.txtForgot)
        sharedPreferences = getSharedPreferences(getString(R.string.shared_preferences),
            MODE_PRIVATE)

        var isLoggedIn = sharedPreferences.getBoolean("isLoggedIn",false)

        if(isLoggedIn){
            val intent = Intent(this@LoginActivity,WelcomeActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnLogin.setOnClickListener{
            var mobile = edtMobile.text.toString()
            var password = edtPass.text.toString()
            if(mobile.isNotEmpty() &&password.isNotEmpty() &&mobile.length==10){
                mobile = edtMobile.text.toString()
                password = edtPass.text.toString()

                val intent = Intent(this@LoginActivity,WelcomeActivity::class.java)

                savePreferences(mobile!!, password!!)


                startActivity(intent)
                finish()
            }
        }
        txtSignUp.setOnClickListener{
            val intent = Intent(this@LoginActivity,RegistrationActivity::class.java)
            startActivity(intent)
        }
        txtForgot.setOnClickListener {
            val intent = Intent(this@LoginActivity,ForgotPasswordActivity::class.java)
            startActivity(intent)
        }
    }
    private fun savePreferences(mobile:String, password:String){
        sharedPreferences.edit().putBoolean("isLoggedIn",true).apply()
        sharedPreferences.edit().putString("mobile",mobile).apply()
        sharedPreferences.edit().putString("password",password).apply()
    }

}
