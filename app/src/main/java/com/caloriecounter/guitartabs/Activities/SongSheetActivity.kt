package com.caloriecounter.guitartabs.Activities

import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.view.Window
import com.caloriecounter.guitartabs.R

class SongSheetActivity : AppCompatActivity() {
    private var PRIVATE_MODE = 0
    private val PREF_NAME = "savedSongs"

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_search -> {
                val intent = Intent(this, SearchActivity::class.java)
                startActivity(intent)

                return@OnNavigationItemSelectedListener true
            }

            R.id.navigation_saved -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPref: SharedPreferences = getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_song_sheet)

        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
}