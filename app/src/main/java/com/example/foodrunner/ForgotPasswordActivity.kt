package com.example.foodrunner

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

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
                val intent = Intent(this@ForgotPasswordActivity,DashboardActivity::class.java)
                val bundle = Bundle()
                bundle.putString("data","forgot")
                bundle.putString("mobile",mobile)
                bundle.putString("email",email)
                intent.putExtra("details",bundle)
                startActivity(intent)
            }
        }
    }
    private fun isValidEmail(email: String):Boolean {
        val pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }
}