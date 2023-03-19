package com.example.spydrive

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.time.LocalTime
import java.util.*

@Suppress("DEPRECATION")
class WakeUpTimeActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wake_up_time)

        var cancelBtnAcordou = findViewById<TextView>(R.id.btnCancelAcordou)

        var textViewCurrentDate = findViewById<TextView>(R.id.DataHoje)
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("dd 'de' MMMM 'de' yyyy", Locale("pt", "BR"))
        val currentDate = dateFormat.format(calendar.time)
        textViewCurrentDate.text = currentDate

        var btnContinue = findViewById<LinearLayout>(R.id.btnNextAcordou)

        btnContinue.setOnClickListener() {

            var horaAcordou = findViewById<EditText>(R.id.inputAcordouHr).text.toString()
            var minAcordou = findViewById<EditText>(R.id.inputAcordouMin).text.toString()

            var horaDomiuInt : Int = horaAcordou.toInt()
            var minutoDormiuInt : Int = minAcordou.toInt()

            val sleepWakeUpInMinutes = (horaDomiuInt * 60) + minutoDormiuInt;
            var sleepMinutes : Int = getIntent().getIntExtra("sleepTimeInMinutes", 0);

            val intent = Intent(this, ToSleepTotalActivity::class.java)
                .putExtra("sleepWakeUpInMinutes", sleepWakeUpInMinutes)
                .putExtra("sleepTimeInMinutes", sleepMinutes);
            startActivity(intent)
        }

        cancelBtnAcordou.setOnClickListener() {

            var horaAcordou = findViewById<EditText>(R.id.inputAcordouHr)
            var minAcordou = findViewById<EditText>(R.id.inputAcordouMin)

            horaAcordou.setText("")
            minAcordou.setText("")
        }

        fun onBackPressed() {
            val intent = Intent(this, ToSleepTimeActivity::class.java)
            startActivity(intent)
        }

        var backBtnAcordou = findViewById<LinearLayout>(R.id.btnBackAcordou)
        backBtnAcordou.setOnClickListener() {
            onBackPressed()
        }
    }

}