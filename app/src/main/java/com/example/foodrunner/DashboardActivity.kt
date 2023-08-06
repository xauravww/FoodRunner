package com.example.foodrunner

import android.annotation.SuppressLint
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class DashboardActivity : AppCompatActivity() {
    lateinit var txtDashboard: TextView
    lateinit var sharedPreferences:SharedPreferences

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        sharedPreferences = getSharedPreferences(getString(R.string.shared_preferences),
            MODE_PRIVATE
        )
        txtDashboard = findViewById(R.id.txtDashboard)
        if (intent != null) {
            val details = intent.getBundleExtra("details")
            val data = details?.getString("data")
            when (data) {
                "forgot" -> {
                    val text =
                        "Email : ${details.getString("email")}" + "Mobile  : ${details.getString("mobile")}"
                    txtDashboard.text = text
                }

                "register" -> {
                    val text =
                        "Name : ${details.getString("name")} " + " Email  : ${details.getString("email")} " + " Mobile  : ${
                            details.getString("mobile")
                        } " + " Delivery  : ${details.getString("delivery")} " + " Password  : ${
                            details.getString(
                                "pass"
                            )
                        }"
                    txtDashboard.text = text
                }

                "login" -> {
                    val text =
                        "Mobile ${sharedPreferences.getString("mobile","USER-Anonymous")} " +
                                "Welcome to our app"
                    txtDashboard.text = text
                }

                else -> {
                    val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn",false)
                    if(isLoggedIn){
                        val text =
                            "Mobile ${sharedPreferences.getString("mobile","USER-Anonymous")} " +
                                    "Welcome to our app"
                        txtDashboard.text = text
                    }else{
                        val text = "No data entered"
                        txtDashboard.text = text
                    }
                }
            }


        }
    }
}