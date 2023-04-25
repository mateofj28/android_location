package com.nico.unilocal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val btnButton = findViewById<AppCompatButton>(R.id.btnCreateLocation)

        btnButton.setOnClickListener { navigationToCreateLocation() }
    }

    fun navigationToCreateLocation(){
        val intent = Intent(this, CreateUbicationActivity::class.java)
        startActivity(intent)
    }
}