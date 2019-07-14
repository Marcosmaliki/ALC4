package com.maliki.alc4phase1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_profile.*
import spencerstudios.com.bungeelib.Bungee

class Profile : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        toolbar.setNavigationOnClickListener {
            finish()
            Bungee.zoom(this)
        }
    }
}
