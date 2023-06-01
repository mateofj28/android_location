package com.nico.unilocal

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class ViewMapActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_map)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)


    }

    @SuppressLint("Range")
    override fun onMapReady(p0: GoogleMap) {
        mMap = p0
        // define location
         val sydney = LatLng(-34.0, 151.0)
        // this is for market
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        // this is to show camara for map
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))

        var bd = DataBase(this)
        val locations = ArrayList<Place>()

        val cursor = bd.findAll()
        println(cursor)

        if (cursor!!.moveToFirst()){
            do {
                val lat = cursor.getDouble(cursor.getColumnIndex("lat"))
                val lng = cursor.getDouble(cursor.getColumnIndex("lng"))
                val name = cursor.getString(cursor.getColumnIndex("name"))
                val des = cursor.getString(cursor.getColumnIndex("description"))

                locations.add(Place(name, des, lat, lng))
            } while (cursor!!.moveToNext())
        }

        val markers = ArrayList<MarkerOptions>()


        for (location in locations){
            markers.add(MarkerOptions().position(LatLng(location.lat, location.lng)).title(location.name).snippet(location.description))
        }

        for (marker in markers){
            mMap.addMarker(marker)
        }

        // mMap.moveCamera(CameraUpdateFactory.newLatLng(LatLng(locations[0].lat, locations[0].lng)))


        mMap.setOnMapClickListener{ latLng ->
            val markerOptions = MarkerOptions().position(latLng)
            mMap.addMarker(markerOptions)
        }

        mMap.setOnMarkerClickListener { marker ->
            if (marker.title == ""){
                println(true)
                val intent = Intent(this, CreateUbicationActivity::class.java)
                intent.putExtra("lat", marker.position.latitude)
                intent.putExtra("lng", marker.position.longitude)
                startActivity(intent)
            }
            true
        }


    }

}