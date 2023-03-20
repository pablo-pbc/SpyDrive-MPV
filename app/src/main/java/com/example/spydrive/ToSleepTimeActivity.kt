package com.example.spydrive

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
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
        textViewCurrentDate.text = "Hoje é dia "+currentDate

        //Função clique de chamada da próxima tela
        sleepTime.setOnClickListener() {
            val horaDormiu = findViewById<EditText>(R.id.inputDormiuHr).text.toString();
            val minutoDormiu = findViewById<EditText>(R.id.inputDormiuMin).text.toString();
            var horaDomiuInt : Int = horaDormiu.toInt()
            val minutoDormiuInt : Int = minutoDormiu.toInt()

            if (horaDomiuInt == 0) {
                horaDomiuInt += 24
            } else {
                horaDomiuInt = horaDomiuInt
            }
            val sleepTimeInMinutes = (horaDomiuInt * 60) + minutoDormiuInt;

            val i = Intent(this, WakeUpTimeActivity::class.java)
                .putExtra("sleepTimeInMinutes", sleepTimeInMinutes);
            startActivity(i)
        }

        // encontrando o EditText pelo ID
        val inputDormiuHr = findViewById<EditText>(R.id.inputDormiuHr)
        val filtersHr = arrayOf<InputFilter>(InputFilter.LengthFilter(2))
        inputDormiuHr.filters = filtersHr

        // defina um InputFilter para permitir somente valores numéricos entre 0 e 23
        val minValueHr = 0
        val maxValueHr = 23

        //Inserindo um função ao input de hora que foi dormir, para limitar o valor inserido pelo usuario
        inputDormiuHr.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                // não é necessário implementar esse método
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // não é necessário implementar esse método
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                s?.let {
                    if (it.isNotEmpty()) {
                        // se o valor de entrada não for um número válido, defina o valor do EditText para o valor mínimo
                        val value = it.toString().toIntOrNull()
                        if (value == null || value !in minValueHr..maxValueHr) {
                            inputDormiuHr.setText(minValueHr.toString())
                            inputDormiuHr.setSelection(inputDormiuHr.text.length)
                        }
                    }
                }
            }
        })

        // encontrando o EditText pelo ID
        val inputDormiuMin = findViewById<EditText>(R.id.inputDormiuMin)
        val filtersMin = arrayOf<InputFilter>(InputFilter.LengthFilter(2))
        inputDormiuMin.filters = filtersMin

        // defina um InputFilter para permitir somente valores numéricos entre 0 e 59
        val minValueMin = 0
        val maxValueMin = 59

        //Inserindo um função ao input de minuto que foi dormir, para limitar o valor inserido pelo usuario
        inputDormiuMin.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                // não é necessário implementar esse método
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // não é necessário implementar esse método
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                s?.let {
                    if (it.isNotEmpty()) {
                        // se o valor de entrada não for um número válido, defina o valor do EditText para o valor mínimo
                        val value = it.toString().toIntOrNull()
                        if (value == null || value !in minValueMin..maxValueMin) {
                            inputDormiuMin.setText(minValueMin.toString())
                            inputDormiuMin.setSelection(inputDormiuMin.text.length)
                        }
                    }
                }
            }
        })

        //Função para limpar os valores do EditText
        cancelBtnDormiu.setOnClickListener() {
            val horaDormiu = findViewById<EditText>(R.id.inputDormiuHr);
            val minutoDormiu = findViewById<EditText>(R.id.inputDormiuMin);
            horaDormiu.setText("")
            minutoDormiu.setText("")
        }
    }
}
