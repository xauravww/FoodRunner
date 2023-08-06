package com.example.foodrunner

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.foodrunner.databinding.ActivityForgotPasswordBinding

class ForgotPasswordActivity : AppCompatActivity() {
    lateinit var edtMobile:EditText
    lateinit var edtEmail:EditText
    lateinit var btnNext:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        edtMobile = findViewById(R.id.edtMobile)
        edtEmail = findViewById(R.id.edtEmail)
        btnNext = findViewById(R.id.btnNext)

        btnNext.setOnClickListener {
            val mobile = edtMobile.text.toString()
            val email = edtEmail.text.toString()

            var isEmailValid = false
            if(email.isNotEmpty()){
                isEmailValid = isValidEmail(email)
                if(!isEmailValid){
                    Toast.makeText(this,"Please enter valid email", Toast.LENGTH_SHORT).show()
                }
            }

            if(isEmailValid&& mobile.isNotEmpty() && email.isNotEmpty() && mobile.length==10){
                val intent = Intent(this@ForgotPasswordActivity,ShowForgotDetailsActivity::class.java)
                intent.putExtra("mobile",mobile)
                intent.putExtra("email",email)
                startActivity(intent)
            }
        }
    }
    private fun isValidEmail(email: String):Boolean {
        val pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }
}