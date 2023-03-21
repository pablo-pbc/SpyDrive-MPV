package com.example.spydrive

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.os.SystemClock
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Polyline
import com.google.android.gms.maps.model.PolylineOptions

class TravelStatusActivity : AppCompatActivity(){

    @SuppressLint("MissingInflatedId")
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_travel_status)

        val alarmMgr = getSystemService(Context.ALARM_SERVICE) as AlarmManager

        //val tempoViagem = findViewById<TextView>(R.id.tempoViagem)
        //val tempoAlarme = findViewById<TextView>(R.id.tempoAlarmeDisp)

        //Pegar valor do tempo de viagem
        val tempoViagemTelaAnterior : Int = intent.getIntExtra("tempoViagemEmMinutos", 0);
        //tempoViagem.setText(tempoViagemTelaAnterior)

        val diferencaHoras = tempoViagemTelaAnterior / 60

        println(diferencaHoras)


        val alarmIntent = Intent(this, AlarmReceiver::class.java).let { intent ->
            PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_MUTABLE)
        }

        if (diferencaHoras <= 6)
        {
            alarmMgr.setExactAndAllowWhileIdle(
                AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime() + 240 * 10000,
                alarmIntent
            )
           // tempoAlarme.setText("4 horas")
        }
        else if (diferencaHoras <= 4)
        {
            alarmMgr.setExactAndAllowWhileIdle(
                AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime() + 180 * 10000,
                alarmIntent
            )
           // tempoAlarme.setText("3 Horas")
        }
        else if (diferencaHoras <= 3)
        {
            alarmMgr.setExactAndAllowWhileIdle(
                AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime() + 30 * 10000,
                alarmIntent
            )
           // tempoAlarme.setText("30 minutos")
        }
        else if(diferencaHoras == 1)
        {
            alarmMgr.setExactAndAllowWhileIdle(
                AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime() + 5 * 1000,
                alarmIntent
            )
            //tempoAlarme.setText("10 segundos")
        }
        

    }

}