package com.example.spydrive

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.EditText
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import java.time.LocalTime

class WakeUpTimeActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wake_up_time)


        var btnContinue = findViewById<LinearLayout>(R.id.btnNextAcordou)

        btnContinue.setOnClickListener() {

            var horaAcordou = findViewById<EditText>(R.id.inputAcordouHr).text.toString()
            var minAcordou = findViewById<EditText>(R.id.inputAcordouMin).text.toString()

            println(horaAcordou)
            println(minAcordou)

            var horaDomiuInt : Int = horaAcordou.toInt()
            var minutoDormiuInt : Int = minAcordou.toInt()

            val sleepWakeUpInMinutes = horaDomiuInt * 60 + minutoDormiuInt;

            val intent = Intent(this, ToSleepTotalActivity::class.java)
                .putExtra("sleepWakeUpInMinutes", sleepWakeUpInMinutes);
            startActivity(intent)
        }

    }

}