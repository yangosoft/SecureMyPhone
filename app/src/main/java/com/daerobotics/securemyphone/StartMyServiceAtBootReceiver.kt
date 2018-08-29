package com.daerobotics.securemyphone

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent


class StartMyServiceAtBootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (Intent.ACTION_BOOT_COMPLETED == intent.action) {
            val serviceIntent = Intent(context, MyService::class.java)
            context.startService(serviceIntent)
        }
    }
}