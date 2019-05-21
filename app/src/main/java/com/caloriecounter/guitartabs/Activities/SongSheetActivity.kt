package com.caloriecounter.guitartabs.Activities

import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.caloriecounter.guitartabs.Models.Song
import com.caloriecounter.guitartabs.R
import com.google.gson.Gson

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

        val currentSong = intent.extras.getParcelable<Song>("song")

        val title: TextView = findViewById(R.id.songTitle)
        title.text = currentSong.title + " - " + currentSong.artist

        val chords: TextView = findViewById(R.id.songData)
        chords.text = currentSong.chords
        chords.movementMethod = ScrollingMovementMethod()

        val saveButton: Button = findViewById(R.id.saveButton)

        var alreadySaved = false
        val sharedPreferenceIds = sharedPref.all.map { it.key }
        val id = currentSong.title + currentSong.artist

        for (s: String in sharedPreferenceIds) {
            if (s == id) {
                alreadySaved = true
                saveButton.text = getString(R.string.unsave)
            }
        }

        saveButton.setOnClickListener {
            val gson = Gson()
            val editor = sharedPref.edit()

            if(alreadySaved) {
                sharedPref.edit().remove(id).commit()
                alreadySaved = false
                saveButton.text = getString(R.string.save)
                Toast.makeText(this, "${currentSong.title} has been unsaved.", Toast.LENGTH_SHORT).show()
            } else {
                editor.putString(id, gson.toJson(currentSong))
                editor.apply()
                saveButton.text = getString(R.string.unsave)
                alreadySaved = true
                Toast.makeText(this, "${currentSong.title} has been saved.", Toast.LENGTH_SHORT).show()
            }

        }
    }
}