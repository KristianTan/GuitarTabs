package com.caloriecounter.guitartabs.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import com.caloriecounter.guitartabs.R
import com.caloriecounter.guitartabs.Requests.AsyncAPICall

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)

        val task = AsyncAPICall()
        task.execute()


        setContentView(R.layout.activity_main)
    }
}
