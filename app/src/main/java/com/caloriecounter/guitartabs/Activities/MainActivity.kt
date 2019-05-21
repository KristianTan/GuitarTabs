package com.caloriecounter.guitartabs.Activities

import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.widget.LinearLayoutManager
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import com.caloriecounter.guitartabs.Adapters.SongAdapter
import com.caloriecounter.guitartabs.Models.Song
import com.caloriecounter.guitartabs.R
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {
    private var PRIVATE_MODE = 0
    private val PREF_NAME = "savedSongs"

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_search -> {
                val intent = Intent(this, SearchActivity::class.java)
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

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        setContentView(R.layout.activity_main)

        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        val sharedPreferenceIds = sharedPref.all.map { it.key }
        val saved: ArrayList<Song> = ArrayList()

        for(s : String in sharedPreferenceIds) {
            val gson = Gson()
            val song = gson.fromJson(sharedPref.getString(s, ""), Song::class.java)

            saved.add(song)
        }


        rv_savedSongs.layoutManager = LinearLayoutManager(this)

        rv_savedSongs.adapter = SongAdapter(saved, this)


    }

}