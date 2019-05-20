package com.caloriecounter.guitartabs.Requests

import android.content.Context
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.caloriecounter.guitartabs.Models.Song
import android.text.method.ScrollingMovementMethod
import android.widget.Toast
import kotlin.properties.Delegates.observable


class SongRequest(var context: Context, var chords: TextView, var title: TextView) {
    var song: Song by observable(Song()) { _, _, new ->
        chords.movementMethod = ScrollingMovementMethod()
        chords.text = new.getChords()
        title.text = new.getTitle() + " - " + new.getArtist()
    }

    fun searchSong(query: String) {
        val url = "http://api.guitarparty.com/v2/songs/?query=$query"

        val queue = Volley.newRequestQueue(this.context)

        val stringRequest = object : StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->
                if (response != "{\"objects\": [], \"objects_count\": 0}") {
                    val parser: Parser = Parser()
                    val stringBuilder: StringBuilder = StringBuilder(response)
                    val json: JsonObject = parser.parse(stringBuilder) as JsonObject

                    val count = json.int("objects_count")
                    val objs = json.array<JsonObject>("objects")

                    var results =  ArrayList<Song>()
                    for (i in 0 until count!!) {
                        results.add(Song(objs?.get(i)))
                    }

                    if(results.size == 1) {
                        this.song = results[0]
                    } else {
                        // TODO: Add all items to recyclerView instead of displaying first in textView
                        this.song = results[0]
                    }

                } else {
                    Toast.makeText(context, "Could not find: $query.", Toast.LENGTH_SHORT).show()
                }
            },
            Response.ErrorListener {

            }) {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["Guitarparty-Api-Key"] = "5a4667184767361d93e7e1e96849c2248e6b7b13"
                return headers
            }
        }

        queue.add(stringRequest)
    }

}
