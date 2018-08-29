package com.daerobotics.securemyphone

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log


class CommandReceiver : BroadcastReceiver()  {

    private val TAG = "ServiceExample"
    companion object {
        const val ACTION_STOP = "com.daerobotics.securemyphone.ACTION_STOP"
    }


    override fun onReceive(context: Context, intent: Intent) {
        Log.i(TAG, "Stop request?" + intent.action)
    }

}