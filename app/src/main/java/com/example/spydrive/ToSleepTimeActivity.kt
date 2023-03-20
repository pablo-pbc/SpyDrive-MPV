package com.example.spydrive

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.InputFilter
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

        val cancelBtnDormiu = findViewById<TextView>(R.id.btnCancelDormiu)
        val sleepTime = findViewById<LinearLayout>(R.id.btnNextDormiu)
        val textViewCurrentDate = findViewById<TextView>(R.id.DataHoje)

        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("dd 'de' MMMM 'de' yyyy", Locale("pt", "BR"))
        val currentDate = dateFormat.format(calendar.time)

        //Atribuindo o valor de currentDate a propriedade text do TextView
        textViewCurrentDate.text = currentDate

        //Função clique de chamada da próxima tela
        sleepTime.setOnClickListener() {
            val horaDormiu = findViewById<EditText>(R.id.inputDormiuHr).text.toString();
            val minutoDormiu = findViewById<EditText>(R.id.inputDormiuMin).text.toString();
            val horaDomiuInt : Int = horaDormiu.toInt()
            val minutoDormiuInt : Int = minutoDormiu.toInt()
            val sleepTimeInMinutes = (horaDomiuInt * 60) + minutoDormiuInt;

            val i = Intent(this, WakeUpTimeActivity::class.java)
                .putExtra("sleepTimeInMinutes", sleepTimeInMinutes);
            startActivity(i)
        }

        val inputDormiuHr = findViewById<EditText>(R.id.inputDormiuHr)
        val filtersHr = arrayOf<InputFilter>(InputFilter.LengthFilter(2))
        inputDormiuHr.filters = filtersHr

        val numericFilterHr = InputFilter { source, start, end, dest, dstart, dend ->
            for (i in start until end) {
                if (!Character.isDigit(source[i])) {
                    return@InputFilter ""
                }
            }
            null
        }
        inputDormiuHr.filters = arrayOf(numericFilterHr)

        val inputDormiuMin = findViewById<EditText>(R.id.inputDormiuMin)
        val filtersMin = arrayOf<InputFilter>(InputFilter.LengthFilter(2))
        inputDormiuMin.filters = filtersMin

        val numericFilterMin = InputFilter { source, start, end, dest, dstart, dend ->
            for (i in start until end) {
                if (!Character.isDigit(source[i])) {
                    return@InputFilter ""
                }
            }
            null
        }
        inputDormiuMin.filters = arrayOf(numericFilterMin)


        //Função para limpar os valores do EditText
        cancelBtnDormiu.setOnClickListener() {
            val horaDormiu = findViewById<EditText>(R.id.inputDormiuHr);
            val minutoDormiu = findViewById<EditText>(R.id.inputDormiuMin);
            horaDormiu.setText("")
            minutoDormiu.setText("")
        }
    }
}
