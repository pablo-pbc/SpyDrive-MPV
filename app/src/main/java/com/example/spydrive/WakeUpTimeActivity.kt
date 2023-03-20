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

        val btnContinue = findViewById<LinearLayout>(R.id.btnNextAcordou)
        val cancelBtnAcordou = findViewById<TextView>(R.id.btnCancelAcordou)
        val backBtnAcordou = findViewById<LinearLayout>(R.id.btnBackAcordou)
        val textViewCurrentDate = findViewById<TextView>(R.id.DataHoje)

        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("dd 'de' MMMM 'de' yyyy", Locale("pt", "BR"))
        val currentDate = dateFormat.format(calendar.time)
        textViewCurrentDate.text = currentDate

        //Função para ir para a proxima tela

        btnContinue.setOnClickListener() {

            val horaAcordou = findViewById<EditText>(R.id.inputAcordouHr).text.toString()
            val minAcordou = findViewById<EditText>(R.id.inputAcordouMin).text.toString()
            val horaDomiuInt : Int = horaAcordou.toInt()
            val minutoDormiuInt : Int = minAcordou.toInt()
            val sleepWakeUpInMinutes = (horaDomiuInt * 60) + minutoDormiuInt;

            val sleepMinutes : Int = getIntent().getIntExtra("sleepTimeInMinutes", 0);

            val intent = Intent(this, ToSleepTotalActivity::class.java)
                .putExtra("sleepWakeUpInMinutes", sleepWakeUpInMinutes)
                .putExtra("sleepTimeInMinutes", sleepMinutes);
            startActivity(intent)
        }

        //Função do botão para limpar o valor dos EditText
        cancelBtnAcordou.setOnClickListener() {
            val horaAcordou = findViewById<EditText>(R.id.inputAcordouHr)
            val minAcordou = findViewById<EditText>(R.id.inputAcordouMin)
            horaAcordou.setText("")
            minAcordou.setText("")
        }

        //Atribuição do evento de click ao EditText para chamar a função (onBackPressed())
        backBtnAcordou.setOnClickListener() {
            onBackPressed()
        }
    }

}