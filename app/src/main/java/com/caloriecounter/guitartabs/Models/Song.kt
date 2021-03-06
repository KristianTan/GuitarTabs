package com.caloriecounter.guitartabs.Models

import android.os.Parcelable
import com.beust.klaxon.JsonObject
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Song (
     var chords : String = "",
     var artist : String = "",
     var title : String = ""
) : Parcelable{

    constructor(json: JsonObject?) : this() {
        var artistArray = json?.array<JsonObject>("authors")
        var artistList = artistArray?.get("name")
        this.artist = artistList?.get(0) as String

        this.chords = json?.string("body").toString()

        this.title = json?.string("title").toString()
    }

}