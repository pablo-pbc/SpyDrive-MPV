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
import java.util.*

class ToSleepTimeActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_to_sleep_time)

        var cancelBtnDormiu = findViewById<TextView>(R.id.btnCancelDormiu)
        var sleepTime = findViewById<LinearLayout>(R.id.btnNextDormiu)
        var textViewCurrentDate = findViewById<TextView>(R.id.DataHoje)

        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("dd 'de' MMMM 'de' yyyy", Locale("pt", "BR"))
        val currentDate = dateFormat.format(calendar.time)
        textViewCurrentDate.text = currentDate

        sleepTime.setOnClickListener() {

            var horaDormiu = findViewById<EditText>(R.id.inputDormiuHr).text.toString();
            var minutoDormiu = findViewById<EditText>(R.id.inputDormiuMin).text.toString();


            var horaDomiuInt : Int = horaDormiu.toInt()
            var minutoDormiuInt : Int = minutoDormiu.toInt()

            val sleepTimeInMinutes = (horaDomiuInt * 60) + minutoDormiuInt;

            val i = Intent(this, WakeUpTimeActivity::class.java)
                .putExtra("sleepTimeInMinutes", sleepTimeInMinutes);
            startActivity(i)
        }

        cancelBtnDormiu.setOnClickListener() {

            var horaDormiu = findViewById<EditText>(R.id.inputDormiuHr);
            var minutoDormiu = findViewById<EditText>(R.id.inputDormiuMin);

            horaDormiu.setText("")
            minutoDormiu.setText("")
        }
    }

}
