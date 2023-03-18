package com.example.spydrive

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import java.time.LocalTime

class ToSleepTimeActivity : AppCompatActivity() {

    @SuppressLint("WrongViewCast")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_to_sleep_time)

        var horaDormiuEditText = findViewById<EditText>(R.id.inputDormiuHr).text.toString();
        var minutoDormiuEditText = findViewById<EditText>(R.id.inputDormiuMin).text.toString();

        if (horaDormiuEditText.equals(""))
        {
            horaDormiuEditText = "0"
        }

        if (minutoDormiuEditText.equals(""))
        {
            minutoDormiuEditText = "0"
        }

        val dormiuTime : LocalTime = createTimeWakeUp(horaDormiuEditText.toInt(), minutoDormiuEditText.toInt())

        println(dormiuTime.toString())

        val sleepTime = findViewById<LinearLayout>(R.id.btnNextDormiu)
        sleepTime.setOnClickListener {
            val i = Intent(this, WakeUpTimeActivity::class.java)
            startActivity(i)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun createTimeWakeUp(hour : Int, minute : Int) : LocalTime {
        return LocalTime.of(hour, minute)
    }

}
