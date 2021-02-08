package com.rishav.forecast

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.loader.content.AsyncTaskLoader
import java.net.URL

class MainActivity : AppCompatActivity() {
    val City: String = "dhaka,bd"
    val Api: String = "88de2a373b86a7f9b5b240ec97bd6a59"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        weatherTask().execute()
    }

    inner class weatherTask() : AsyncTask<String, Void, String>() {
        override fun onPreExecute() {
            super.onPreExecute()
            findViewById<ProgressBar>(R.id.loader).visibility = View.VISIBLE
            findViewById<RelativeLayout>(R.id.mainContainer).visibility = View.GONE
            findViewById<TextView>(R.id.errortext).visibility = View.GONE
        }

        override fun doInBackground(vararg p0: String?): String? {
            var response: String?
            try {
                response = URL("api.openweathermap.org/data/2.5/weather?q=$City&appid=$Api")
                    .readText(Charsets.UTF_8)
            } catch (e: Exception) {
                response = null
            }
            return response
        }
    }
}