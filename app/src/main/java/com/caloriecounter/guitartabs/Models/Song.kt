package com.caloriecounter.guitartabs.Models

import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject

class Song {

    private var chords : String = ""
    private var artist : String = ""
    private var title : String = ""

    constructor(json: JsonObject) {
        val wrapped = json.array<JsonObject>("objects")
        var data = wrapped?.get(0)

        var artistArray = data?.array<JsonObject>("authors")
        var artistList = artistArray?.get("name")
        this.artist = artistList?.get(0) as String

        this.chords = data?.string("body").toString()

        this.title = data?.string("title").toString()

        println()

    }

    constructor() {
    }

    fun getChords(): String {
        return this.chords
    }
    fun getTitle(): String {
        return this.title
    }
    fun getArtist(): String {
        return this.artist
    }
}