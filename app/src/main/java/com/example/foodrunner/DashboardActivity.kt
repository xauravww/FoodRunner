package com.example.foodrunner

import android.annotation.SuppressLint
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentManager
import com.example.foodrunner.fragment.FavouriteFragment
import com.example.foodrunner.fragment.HomeFragment
import com.example.foodrunner.fragment.ProfileFragment
import com.example.foodrunner.fragment.QuestionFragment
import com.google.android.material.navigation.NavigationView

class DashboardActivity : AppCompatActivity() {
    lateinit var txtDashboard: TextView
    lateinit var sharedPreferences: SharedPreferences
    lateinit var drawerLayout: DrawerLayout
    lateinit var coordinatorLayout: CoordinatorLayout
    lateinit var toolbar: Toolbar
    lateinit var frameLayout: FrameLayout
    lateinit var navigationView: NavigationView

    var prevMenuItem: MenuItem? = null

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        sharedPreferences = getSharedPreferences(
            getString(R.string.shared_preferences),
            MODE_PRIVATE
        )
        txtDashboard = findViewById(R.id.txtDashboard)
        drawerLayout = findViewById(R.id.drawerLayout)
        coordinatorLayout = findViewById(R.id.coordinatorLayout)
        toolbar = findViewById(R.id.toolbar)
        frameLayout = findViewById(R.id.frameLayout)
        navigationView = findViewById(R.id.navigationView)
        setUpToolbar()
        openHome()
        val actionBarDrawertoggle = ActionBarDrawerToggle(
            this@DashboardActivity,
            drawerLayout,
            R.string.open_drawer,
            R.string.close_drawer
        )
        drawerLayout.addDrawerListener(actionBarDrawertoggle)
        actionBarDrawertoggle.syncState()


        navigationView.setNavigationItemSelectedListener {
            if (prevMenuItem != null) {
                prevMenuItem?.isChecked = false
            }
            it.isCheckable = true
            it.isChecked = true
            prevMenuItem = it

            when (it.itemId) {
                R.id.home -> {
                    openHome()
                }

                R.id.myProfile -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout, ProfileFragment())
                        .commit()
                    drawerLayout.closeDrawers()
                    supportActionBar?.title= "Profile"
                    txtDashboard.visibility = View.GONE
                }

                R.id.favRestaurant -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout, FavouriteFragment())

                        .commit()
                    drawerLayout.closeDrawers()
                    supportActionBar?.title= "Favourites"
                    txtDashboard.visibility = View.GONE
                }

                R.id.faqs -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout, QuestionFragment())

                        .commit()
                    drawerLayout.closeDrawers()
                    supportActionBar?.title= "FAQs"
                    txtDashboard.visibility = View.GONE
                }

                R.id.logOut -> {
                    Toast.makeText(this@DashboardActivity, "Add Logout feature", Toast.LENGTH_SHORT)
                        .show()
                    txtDashboard.visibility = View.GONE
                }

            }


            return@setNavigationItemSelectedListener true
        }

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
                        "Mobile ${sharedPreferences.getString("mobile", "USER-Anonymous")} " +
                                "Welcome to our app"
                    txtDashboard.text = text
                }

                else -> {
                    val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)
                    if (isLoggedIn) {
                        val text =
                            "Mobile ${sharedPreferences.getString("mobile", "USER-Anonymous")} " +
                                    "Welcome to our app"
                        txtDashboard.text = text
                    } else {
                        val text = "No data entered"
                        txtDashboard.text = text
                    }
                }
            }


        }
    }

    private fun setUpToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Toolbar Title"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun openHome() {
        val transaction = supportFragmentManager.beginTransaction()
        val fragment = HomeFragment()
        transaction.replace(R.id.frameLayout, fragment)
        transaction.commit()
        drawerLayout.closeDrawers()
        supportActionBar?.title = "Dashboard"
        navigationView.setCheckedItem(R.id.home)
        txtDashboard.visibility = View.GONE
    }
}