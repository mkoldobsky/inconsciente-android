package com.inconsciente.colectiv.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.inconsciente.colectiv.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.my_toolbar))
    }
}
