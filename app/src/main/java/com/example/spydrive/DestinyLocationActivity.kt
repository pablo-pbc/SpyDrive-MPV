package com.example.spydrive

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback

class DestinyLocationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_destiny_location)

        val travelStatus = findViewById<LinearLayout>(R.id.btnNextComecarViagem)
        val locationGoal = findViewById<EditText>(R.id.locationGoal).text

        travelStatus.setOnClickListener() {
            val i = Intent(this, TravelStatusActivity::class.java)
                .putExtra("locationGoal", locationGoal)
            startActivity(i)
        }

    }

}