package com.caloriecounter.guitartabs.Requests

import android.os.AsyncTask
import android.widget.TextView
import java.io.IOException
import java.net.URL
import org.json.JSONArray
import org.json.JSONObject



class AsyncAPICall() : AsyncTask<String, Void, String>() {

    override fun onPreExecute() {
        super.onPreExecute()
    }


    override fun doInBackground(vararg params: String?): String {
//        val url = "http://api.guitarparty.com/v2/songs/?query=" + params[0]
//        val client = OkHttpClient()
//
//        val request = Request.Builder()
//            .header("Guitarparty-Api-Key", "5a4667184767361d93e7e1e96849c2248e6b7b13")
//            .url(url)
//            .build()
//
//        client.newCall(request).enqueue(object : Callback {
//            override fun onFailure(call: Call, e: IOException) {}
//            override fun onResponse(call: Call, response: Response) = println(response)
//        })
        return ""

    }

    override fun onPostExecute(result: String?) {
        println(result)
        super.onPostExecute(result)
    }
}
