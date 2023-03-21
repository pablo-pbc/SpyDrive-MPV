package com.example.spydrive

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        // Executa a ação desejada quando o alarme for disparado
        Log.d("AlarmReceiver", "Alarme disparado")
        println("FOI")
    }
}
