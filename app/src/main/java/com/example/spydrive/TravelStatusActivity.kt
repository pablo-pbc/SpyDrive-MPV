package com.example.spydrive

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Polyline
import com.google.android.gms.maps.model.PolylineOptions

class TravelStatusActivity : AppCompatActivity() , OnMapReadyCallback, LocationListener {

    private lateinit var mMap: GoogleMap
    private lateinit var mLocationManager: LocationManager
    private lateinit var mPolylineOptions: PolylineOptions
    private lateinit var mPolyline: Polyline

    val REQUEST_LOCATION_PERMISSION = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_travel_status)

        //var destinyLocationAddres = findViewById<EditText>(R.id.locationGoal).text

        val map = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        map.getMapAsync(this)

        val locationGoal: String? = intent.getStringExtra("locationGoal")

        mLocationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0f, this)

        mPolylineOptions = PolylineOptions().color(Color.RED)
        mPolyline = mMap.addPolyline(mPolylineOptions)

    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap


    }

    fun isPermissionGranted(): Boolean {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
    }

    @SuppressLint("MissingPermission")
    private fun enableMyLocation() {
        if (isPermissionGranted()) {
            mMap.isMyLocationEnabled = true
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_LOCATION_PERMISSION)
        }
    }



    override fun onLocationChanged(location: Location) {
        location?.let {
            val latLng = LatLng(it.latitude, it.longitude)
            mPolylineOptions.add(latLng)
            mPolyline.points = mPolylineOptions.points
        }
    }



}