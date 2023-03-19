package com.example.spydrive

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class ToSleepTotalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sleep_total_time)

        var resultadoHora = findViewById<EditText>(R.id.inputTempoSonoHr)
        var resultadoMin = findViewById<EditText>(R.id.inputTempoSonoMin)

        var sleepMinutes : Int = getIntent().getIntExtra("sleepTimeInMinutes", 0);
        var wakeUpMinutes : Int = getIntent().getIntExtra("sleepWakeUpInMinutes", 0);

        val diferencaMinutos = wakeUpMinutes - sleepMinutes

        // Converte a diferença em horas e minutos
        val diferencaHoras = diferencaMinutos / 60
        val diferencaMinutosRestantes = diferencaMinutos % 60

        var diferencaHorasStr = diferencaHoras.toString()
        var diferencaMinutosStr = diferencaMinutosRestantes.toString()

        if (diferencaHorasStr.toString().length == 1)
        {
            diferencaHorasStr = "0" + diferencaHorasStr
        }

        if (diferencaMinutosStr.length == 1)
        {
            diferencaHorasStr = "0" + diferencaMinutosStr
        }

        println(diferencaHoras)
        println(diferencaMinutosRestantes)

        resultadoHora.setText(diferencaHorasStr)
        resultadoMin.setText(diferencaHorasStr)


    }
}