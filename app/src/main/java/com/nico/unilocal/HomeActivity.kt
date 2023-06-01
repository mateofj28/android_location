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
        val btnButtonShowMap = findViewById<AppCompatButton>(R.id.btnShowMap)
        val btnButtonShowPlaces = findViewById<AppCompatButton>(R.id.btnShowPlaces)

        btnButton.setOnClickListener { navigationToCreateLocation() }
        btnButtonShowMap.setOnClickListener { navigationToShowMap() }
        btnButtonShowPlaces.setOnClickListener { navigationToShowPlaces() }
    }

    private fun navigationToCreateLocation(){
        val intent = Intent(this, CreateUbicationActivity::class.java)
        startActivity(intent)
    }

    private fun navigationToShowMap(){
        val intent = Intent(this, ViewMapActivity::class.java)
        startActivity(intent)
    }

    private fun navigationToShowPlaces(){
        val intent = Intent(this, PlacesActivityRcv::class.java)
        startActivity(intent)
    }

}