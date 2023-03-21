package com.example.spydrive

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class DestinyLocationActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_destiny_location)

        val cancelBtnDormiu = findViewById<TextView>(R.id.btnCancelTempoViagem)
        val nextScreenTempoViagem = findViewById<LinearLayout>(R.id.btnNextTempoViagem)
        val textViewCurrentDate = findViewById<TextView>(R.id.DataHoje)

        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("dd 'de' MMMM 'de' yyyy", Locale("pt", "BR"))
        val currentDate = dateFormat.format(calendar.time)

        //Atribuindo o valor de currentDate a propriedade text do TextView
        textViewCurrentDate.text = "Hoje é dia "+currentDate

        //Função clique de chamada da próxima tela
        nextScreenTempoViagem.setOnClickListener() {
            val horaTempoViagem = findViewById<EditText>(R.id.inputTempoViagemHr).text.toString();
            val minutoTempoViagem = findViewById<EditText>(R.id.inputTempoViagemMin).text.toString();
            var horaTempoViagemInt : Int = horaTempoViagem.toInt()
            val minutoTempoViagemInt : Int = minutoTempoViagem.toInt()

            if (horaTempoViagemInt == 0) {
                horaTempoViagemInt += 24
            } else {
                horaTempoViagemInt = horaTempoViagemInt
            }
            val sleepTimeInMinutes = (horaTempoViagemInt * 60) + minutoTempoViagemInt;

            val i = Intent(this, WakeUpTimeActivity::class.java)
                .putExtra("sleepTimeInMinutes", sleepTimeInMinutes);
            startActivity(i)
        }

        // encontrando o EditText pelo ID
        val inputTempoViagemHr = findViewById<EditText>(R.id.inputTempoViagemHr)
        val filtersHr = arrayOf<InputFilter>(InputFilter.LengthFilter(2))
        inputTempoViagemHr.filters = filtersHr

        // defina um InputFilter para permitir somente valores numéricos entre 0 e 23
        val minValueHr = 0
        val maxValueHr = 23

        //Inserindo um função ao input de hora que foi dormir, para limitar o valor inserido pelo usuario
        inputTempoViagemHr.addTextChangedListener(object : TextWatcher {
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
                            inputTempoViagemHr.setText(minValueHr.toString())
                            inputTempoViagemHr.setSelection(inputTempoViagemHr.text.length)
                        }
                    }
                }
            }
        })

        // encontrando o EditText pelo ID
        val inputTempoViagemMin = findViewById<EditText>(R.id.inputTempoViagemMin)
        val filtersMin = arrayOf<InputFilter>(InputFilter.LengthFilter(2))
        inputTempoViagemMin.filters = filtersMin

        // defina um InputFilter para permitir somente valores numéricos entre 0 e 59
        val minValueMin = 0
        val maxValueMin = 59

        //Inserindo um função ao input de minuto que foi dormir, para limitar o valor inserido pelo usuario
        inputTempoViagemMin.addTextChangedListener(object : TextWatcher {
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
                            inputTempoViagemMin.setText(minValueMin.toString())
                            inputTempoViagemMin.setSelection(inputTempoViagemMin.text.length)
                        }
                    }
                }
            }
        })

        //Função para limpar os valores do EditText
        cancelBtnDormiu.setOnClickListener() {
            val horaTempoViagem = findViewById<EditText>(R.id.inputTempoViagemHr);
            val minutoTempoViagem = findViewById<EditText>(R.id.inputTempoViagemMin);
            horaTempoViagem.setText("")
            minutoTempoViagem.setText("")
        }
    }
}