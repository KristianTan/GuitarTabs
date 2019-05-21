package com.caloriecounter.guitartabs.Activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.SearchView
import android.widget.Toast
import com.caloriecounter.guitartabs.Adapters.SongAdapter
import com.caloriecounter.guitartabs.Models.Song
import com.caloriecounter.guitartabs.R
import com.caloriecounter.guitartabs.Requests.SongRequest
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
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


        rv_search_results.layoutManager = LinearLayoutManager(this)

        rv_search_results.adapter = SongAdapter(ArrayList<Song>(), this)

        val songRequest = SongRequest(this, rv_search_results.adapter as SongAdapter)

        val search: SearchView = findViewById(R.id.searchBar)
        search.isIconified = false
        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                songRequest.searchSong(query)
                return false
            }
        })


    }

}