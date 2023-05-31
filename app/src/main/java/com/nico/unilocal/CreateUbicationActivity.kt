package com.nico.unilocal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import com.google.android.gms.maps.model.LatLng

class CreateUbicationActivity : AppCompatActivity() {

    private lateinit var txDescription : AppCompatEditText
    private lateinit var txName : AppCompatEditText
    private lateinit var txSchedule : AppCompatEditText
    private lateinit var txPhone: AppCompatEditText
    private lateinit var txCategory : AppCompatEditText
    var lat: Double = 0.0
    var lng: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_ubication)

        txDescription = findViewById(R.id.txtDescription)
        txName = findViewById(R.id.txtName)
        txSchedule = findViewById(R.id.txtSchedule)
        txPhone = findViewById(R.id.txtPhone)
        txCategory = findViewById(R.id.txtCategory)

        lat = intent.getDoubleExtra("lat", 0.0)
        lng = intent.getDoubleExtra("lng", 0.0)


        val btnCreatePlace = findViewById<AppCompatButton>(R.id.btnCreatePlace)
        btnCreatePlace.setOnClickListener { saveData() }
    }

    private fun saveData(){
        var bd = DataBase(this)

        if (txName.text.toString().isNotEmpty() || txDescription.text.toString().isNotEmpty() || txSchedule.text.toString().isNotEmpty() || txPhone.text.toString().isNotEmpty() || txCategory.text.toString().isNotEmpty()){
            var newPlace = Place(txName.text.toString(), txDescription.text.toString(), txSchedule.text.toString(), txPhone.text.toString(), txCategory.text.toString(), "null", "null",  lat, lng, 0, null)
            println(newPlace)
            var message = bd.insertPlace(newPlace)

            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

            if (message == "Inserted (ok)"){
                clearData()
                super.onBackPressed()
            }
        } else {
            Toast.makeText(this, "Insert all data please.", Toast.LENGTH_SHORT).show()
        }

    }

    private fun clearData(){
        txName.setText("")
        txDescription.setText("")
        txSchedule.setText("")
        txPhone.setText("")
        txCategory.setText("")
    }
}