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

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_to_sleep_time)

        var sleepTime = findViewById<LinearLayout>(R.id.btnNextDormiu)

        sleepTime.setOnClickListener() {

            var horaDormiu = findViewById<EditText>(R.id.inputDormiuHr).text.toString();
            var minutoDormiu = findViewById<EditText>(R.id.inputDormiuMin).text.toString();

            println(horaDormiu)
            println(minutoDormiu)

            var horaDomiuInt : Int = horaDormiu.toInt()
            var minutoDormiuInt : Int = minutoDormiu.toInt()

            val sleepTimeInMinutes = horaDomiuInt * 60 + minutoDormiuInt;

            val i = Intent(this, WakeUpTimeActivity::class.java)
                .putExtra("sleepTimeInMinutes", sleepTimeInMinutes);

            startActivity(i)
        }
    }

}
