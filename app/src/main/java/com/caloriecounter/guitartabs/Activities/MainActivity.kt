package com.caloriecounter.guitartabs.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.view.Window
import android.view.WindowManager
import android.widget.SearchView
import android.widget.TextView
import com.caloriecounter.guitartabs.R
import com.caloriecounter.guitartabs.Requests.AsyncAPICall

class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_saved -> {
                println("saved")
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_search -> {
                println("search")
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)

//        val task = AsyncAPICall()
//        task.execute()

        setContentView(R.layout.activity_main)

        val bottomNavigation : BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
}
