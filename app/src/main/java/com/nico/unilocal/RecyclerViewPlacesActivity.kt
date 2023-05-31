package com.nico.unilocal

import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewPlacesActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var db: DataBase
    private lateinit var newArrayPlace : ArrayList<Place>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view_places)

        recyclerView = findViewById(R.id.RcvPlaces)

        db = DataBase(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        getPlaces()
    }

    private fun getPlaces(){
        var newCursor: Cursor? = db.findAll()
        newArrayPlace = ArrayList()
        while (newCursor!!.moveToFirst()){
            val uName = newCursor.getString(0)
            val uDesc = newCursor.getString(1)
            val uSchedule = newCursor.getString(2)
            val uPhone = newCursor.getString(3)


            println("nombre: $uName")
            println("des: $uDesc")
            println("schedule: $uSchedule")
            println("des: $uPhone")


            newArrayPlace.add(Place(uName, uDesc, uSchedule, uPhone, "1", "", "", 4.22, 1.44, 0, null))
        }
        recyclerView.adapter = AdapterRecyclerPlaces(newArrayPlace)
    }
}