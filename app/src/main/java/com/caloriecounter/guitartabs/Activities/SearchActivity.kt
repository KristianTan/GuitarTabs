package com.caloriecounter.guitartabs.Activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.SearchView
import android.widget.Toast
import com.caloriecounter.guitartabs.Models.Song
import com.caloriecounter.guitartabs.R
import com.caloriecounter.guitartabs.Requests.SongRequest
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {
    private var PRIVATE_MODE = 0
    private val PREF_NAME = "savedSongs"
//    private var currentSong: Song = Song()


    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_saved -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        val sharedPref: SharedPreferences = getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        val songRequest = SongRequest(this, findViewById(R.id.chords), findViewById(R.id.title))

        val search: SearchView = this.findViewById(R.id.searchBar)
        search.isIconified = false
        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                songRequest.searchSong(query)
                saveButton.visibility = View.VISIBLE
                return false
            }

        })

        val saveButton: Button = findViewById(R.id.saveButton)
        saveButton.visibility = View.GONE
        saveButton.setOnClickListener {
            if (!songRequest.song.getTitle().isBlank()) {
                val sharedPreferenceIds = sharedPref.all.map { it.key }
                val currentSong = songRequest.song
                val id = currentSong.getTitle() + currentSong.getArtist()

                var alreadySaved = false
                saveButton.visibility = View.GONE

                for(s : String in sharedPreferenceIds) {
                    if(s == id) {
                        alreadySaved = true
                        Toast.makeText(this, "Already saved this song.", Toast.LENGTH_LONG).show()
                    }
                }

                if(!alreadySaved) {
                    val gson = Gson()

                    val editor = sharedPref.edit()
                    editor.putString(id, gson.toJson(currentSong))
                    editor.apply()

                    Toast.makeText(this, "Saved.", Toast.LENGTH_SHORT).show()

                }
            }
        }
    }

}