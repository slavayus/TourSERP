package com.tour.serp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.tour.serp.ui.tour.ToursFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ToursFragment.startFragment(supportFragmentManager)
    }
}
