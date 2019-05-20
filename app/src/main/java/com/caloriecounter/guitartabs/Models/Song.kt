package com.caloriecounter.guitartabs.Models

import com.beust.klaxon.JsonObject

class Song {

    private var chords : String = ""
    private var artist : String = ""
    private var title : String = ""

    constructor(json: JsonObject?) {
        var artistArray = json?.array<JsonObject>("authors")
        var artistList = artistArray?.get("name")
        this.artist = artistList?.get(0) as String

        this.chords = json?.string("body").toString()

        this.title = json?.string("title").toString()
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