package com.caloriecounter.guitartabs.Requests

import android.os.AsyncTask
import java.net.URL

class AsyncAPICall() : AsyncTask<String, Void, String>() {

    override fun onPreExecute() {
        super.onPreExecute()
    }


    override fun doInBackground(vararg params: String?): String {
        val result = URL("http://api.guitarparty.com/v2/songs/?query=californication").readText()

        return result;
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
    }

}
