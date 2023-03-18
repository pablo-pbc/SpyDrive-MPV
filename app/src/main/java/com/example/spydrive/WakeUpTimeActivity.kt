package com.example.spydrive

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.EditText
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import java.time.LocalTime
import java.util.Date

class WakeUpTimeActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wake_up_time)

        var horaAcordouEditText = findViewById<EditText>(R.id.inputAcordouHr).text.toString()
        var minAcordouEditText = findViewById<EditText>(R.id.inputAcordouMin).text.toString()

        if (horaAcordouEditText.equals(""))
        {
            horaAcordouEditText = "0"
        }

        if (minAcordouEditText.equals(""))
        {
            minAcordouEditText = "0"
        }

        val wakeUpTime : LocalTime = createTimeWakeUp(horaAcordouEditText.toInt(), minAcordouEditText.toInt())

        val wakeUpTimeButton = findViewById<LinearLayout>(R.id.btnNextAcordou)
        wakeUpTimeButton.setOnClickListener {
            val i = Intent(this, ToSleepTotalActivity::class.java)
            startActivity(i)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun createTimeWakeUp(hour : Int, minute : Int) : LocalTime {
        return LocalTime.of(hour, minute)
    }
}