package com.example.foodrunner

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.foodrunner.databinding.ActivityRegistrationBinding
import java.util.regex.Pattern

class RegistrationActivity : AppCompatActivity() {
    lateinit var imgArrow:ImageView
    lateinit var edtName:EditText
    lateinit var edtEmail:EditText
    lateinit var edtMobile:EditText
    lateinit var edtDelivery:EditText
    lateinit var edtChoosePass:EditText
    lateinit var edtConfirmPass:EditText
    lateinit var btnRegister:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        imgArrow = findViewById(R.id.imgArrow)
        edtName = findViewById(R.id.edtName)
        edtEmail = findViewById(R.id.edtEmail)
        edtMobile = findViewById(R.id.edtMobile)
        edtDelivery = findViewById(R.id.edtDelivery)
        edtChoosePass = findViewById(R.id.edtChoosePass)
        edtConfirmPass = findViewById(R.id.edtConfirmPass)
        btnRegister = findViewById(R.id.btnRegister)


        imgArrow.setOnClickListener {
            val intent = Intent(this@RegistrationActivity,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        btnRegister.setOnClickListener {
            val name  = edtName.text.toString()
            val email = edtEmail.text.toString()
            val mobile = edtMobile.text.toString()
            val delivery = edtDelivery.text.toString()
            val pass = edtChoosePass.text.toString()
            val confirmpass = edtConfirmPass.text.toString()

            var isEmailValid = false
            if(email.isNotEmpty()){
                isEmailValid = isValidEmail(email)
                if(!isEmailValid){
                    Toast.makeText(this,"Please enter valid email",Toast.LENGTH_SHORT).show()
                }
            }

            if(pass!=confirmpass){
                Toast.makeText(this, "Password must be same",Toast.LENGTH_SHORT).show()
            }
            if (isEmailValid&& name.isNotEmpty() && email.isNotEmpty() && mobile.isNotEmpty() && delivery.isNotEmpty() && pass.isNotEmpty() && confirmpass.isNotEmpty() && pass==confirmpass && mobile.length==10){

                val intent = Intent(this@RegistrationActivity,ShowRegistrationDetailsActivity::class.java)
                intent.putExtra("name",name)
                intent.putExtra("email",email)
                intent.putExtra("mobile",mobile)
                intent.putExtra("delivery",delivery)
                intent.putExtra("pass",confirmpass)
                startActivity(intent)
                finish()
            }
        }


    }

    private fun isValidEmail(email: String):Boolean {
        val pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }


}




