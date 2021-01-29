package com.eg.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val api = DataBaseAPI()
        val params = mapOf(
                "api_key" to BuildConfig.API_KEY,
                "sort_by" to "popularity.desc"
        )
        api.getRandomMovie(params)
    }
}