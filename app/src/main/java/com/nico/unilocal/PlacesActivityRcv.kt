package com.nico.unilocal

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PlacesActivityRcv : AppCompatActivity() {
    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_places_rcv)

        val places = mutableListOf<DataPlace>()
        val db = DataBase(this)
        val cursor = db.findAll()

        while(cursor!!.moveToNext()){
            val name = cursor.getString(cursor.getColumnIndex("name"))
            val des = cursor.getString(cursor.getColumnIndex("description"))
            places.add(DataPlace(name, des))
        }

        cursor.close()
        db.close()

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerPlaces)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = DataPlacesAdapter(places)
        recyclerView.adapter = adapter

    }
}