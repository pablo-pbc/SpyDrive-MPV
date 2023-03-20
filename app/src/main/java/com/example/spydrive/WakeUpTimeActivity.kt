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
        textViewCurrentDate.text = "Hoje é dia "+currentDate

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

        //Inserindo filtro de inserção máxima de caracteres, no caso, no máximo 2 caracteres
        val inputAcordouHr = findViewById<EditText>(R.id.inputAcordouHr)
        val filtersHr = arrayOf<InputFilter>(InputFilter.LengthFilter(2))
        inputAcordouHr.filters = filtersHr

        // defina um InputFilter para permitir somente valores numéricos entre 0 e 23
        val minValueHr = 0
        val maxValueHr = 23

        //Inserindo um função ao input de hora que acordou, para limitar o valor inserido pelo usuario
        inputAcordouHr.addTextChangedListener(object : TextWatcher {
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
                            inputAcordouHr.setText(minValueHr.toString())
                            inputAcordouHr.setSelection(inputAcordouHr.text.length)
                        }
                    }
                }
            }
        })

        val inputAcordouMin = findViewById<EditText>(R.id.inputAcordouMin)
        val filtersMin = arrayOf<InputFilter>(InputFilter.LengthFilter(2))
        inputAcordouMin.filters = filtersMin

        // defina um InputFilter para permitir somente valores numéricos entre 0 e 23
        val minValueMin = 0
        val maxValueMin = 59

        //Inserindo um função ao input do minuto que acordou, para limitar o valor inserido pelo usuario
        inputAcordouMin.addTextChangedListener(object : TextWatcher {
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
                            inputAcordouMin.setText(minValueHr.toString())
                            inputAcordouMin.setSelection(inputAcordouMin.text.length)
                        }
                    }
                }
            }
        })

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