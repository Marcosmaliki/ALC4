package com.maliki.alc4phase1

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import spencerstudios.com.bungeelib.Bungee

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        profile.setOnClickListener {
            startActivity(Intent(this, Profile::class.java))
            Bungee.swipeLeft(this)
        }
        about.setOnClickListener {
            startActivity(Intent(this, About::class.java))
            Bungee.swipeLeft(this)
        }
    }
}
