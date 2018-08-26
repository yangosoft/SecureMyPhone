package com.daerobotics.securemyphone

import android.app.IntentService
import android.content.Intent
import android.util.Log

class MyService : IntentService("MyIntentService") {

    private val TAG = "ServiceExample"

    override fun onHandleIntent(arg0: Intent?) {
        Log.i(TAG, "Intent Service started")
    }


}
