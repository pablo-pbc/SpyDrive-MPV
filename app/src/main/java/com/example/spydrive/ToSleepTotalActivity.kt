package com.example.spydrive

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class ToSleepTotalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sleep_total_time)

        var textViewCurrentDate = findViewById<TextView>(R.id.DataHoje)
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("dd 'de' MMMM 'de' yyyy", Locale("pt", "BR"))
        val currentDate = dateFormat.format(calendar.time)
        textViewCurrentDate.text = currentDate

        var resultadoHora = findViewById<TextView>(R.id.inputTempoSonoHr)
        var resultadoMin = findViewById<TextView>(R.id.inputTempoSonoMin)

        var sleepMinutes : Int = getIntent().getIntExtra("sleepTimeInMinutes", 0);
        var wakeUpMinutes : Int = getIntent().getIntExtra("sleepWakeUpInMinutes", 0);

        var diferencaMinutos =  wakeUpMinutes - sleepMinutes

        // Se a diferença for negativa, significa que o usuário dormiu depois de ter acordado (provavelmente trocou os valores dos EditTexts)
        if (diferencaMinutos < 0) {
            diferencaMinutos += 24 * 60
        }

        // Converte a diferença em horas e minutos
        val diferencaHoras = diferencaMinutos / 60
        val diferencaMinutosRestantes = diferencaMinutos % 60

        var diferencaHorasStr = diferencaHoras.toString()
        var diferencaMinutosStr = diferencaMinutosRestantes.toString()

        resultadoHora.text = diferencaHorasStr.toString()
        resultadoMin.text = diferencaMinutosStr.toString()

    }
}