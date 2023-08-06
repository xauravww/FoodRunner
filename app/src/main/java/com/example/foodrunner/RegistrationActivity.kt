package com.example.foodrunner

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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

                val intent = Intent(this@RegistrationActivity,DashboardActivity::class.java)
                val bundle = Bundle()
                bundle.putString("data","register")
                bundle.putString("name",name)
                bundle.putString("email",email)
                bundle.putString("mobile",mobile)
                bundle.putString("delivery",delivery)
                bundle.putString("pass",confirmpass)
                intent.putExtra("details",bundle)
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




