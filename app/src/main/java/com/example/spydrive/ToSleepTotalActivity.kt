package com.example.spydrive

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextClock
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

@Suppress("DEPRECATION")
class ToSleepTotalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sleep_total_time)

        val textViewCurrentDate = findViewById<TextView>(R.id.DataHoje)
        val resultadoHora = findViewById<TextView>(R.id.inputTempoSonoHr)
        val resultadoMin = findViewById<TextView>(R.id.inputTempoSonoMin)
        val bgStrokeBadSleep = findViewById<LinearLayout>(R.id.linearLayoutTotalSleep)
        val txtSleepQuality = findViewById<TextView>(R.id.msgSonoQualidade)
        val backBtnTempoSono = findViewById<LinearLayout>(R.id.btnBackTempoSono)

        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("dd 'de' MMMM 'de' yyyy", Locale("pt", "BR"))
        val currentDate = dateFormat.format(calendar.time)
        textViewCurrentDate.text = "Hoje é dia "+currentDate

        val sleepMinutes : Int = getIntent().getIntExtra("sleepTimeInMinutes", 0);
        val wakeUpMinutes : Int = getIntent().getIntExtra("sleepWakeUpInMinutes", 0);

        var diferencaMinutos =  (wakeUpMinutes) - sleepMinutes

        // Se a diferença for negativa, significa que o usuário dormiu depois de ter acordado (provavelmente trocou os valores dos EditTexts)
        if (diferencaMinutos <= 0) {
            diferencaMinutos += 24 * 60
        }

        // Converte a diferença em horas e minutos
        val diferencaHoras = diferencaMinutos / 60
        val diferencaMinutosRestantes = diferencaMinutos % 60
        val diferencaHorasStr = diferencaHoras.toString()
        val diferencaMinutosStr = diferencaMinutosRestantes.toString()

        if (diferencaHoras <= 9 ) {
            resultadoHora.text = "0" + diferencaHorasStr.toString()

            if (diferencaMinutosRestantes <= 9) {
                resultadoMin.text = "0" + diferencaMinutosStr.toString()
            } else {
                resultadoMin.text = diferencaMinutosStr.toString()
            }
        } else {
            resultadoHora.text = diferencaHorasStr.toString()
            resultadoMin.text = diferencaMinutosStr.toString()
        }

        //Alterando o texto final dependendo do tempo de sono do usuário
        if (diferencaHoras in 5..6){
            bgStrokeBadSleep.setBackgroundResource(R.drawable.rounded_input_time_medium)
            txtSleepQuality.text = "Não foi pouco, mas ainda não é o ideal!"
            txtSleepQuality.setTextColor(Color.parseColor("#fb8500"))
        } else if (diferencaHoras < 5) {
            bgStrokeBadSleep.setBackgroundResource(R.drawable.rounded_input_time_bad)
            txtSleepQuality.text = "Pelo visto você dormiu pouco, hein!"
            txtSleepQuality.setTextColor(Color.parseColor("red"))
        }

        backBtnTempoSono.setOnClickListener() {
            onBackPressed()
        }

        val destinyLocation = findViewById<LinearLayout>(R.id.btnNextTempoSono)

        destinyLocation.setOnClickListener() {
            val i = Intent(this, DestinyLocationActivity::class.java)
            startActivity(i)
        }

    }
}