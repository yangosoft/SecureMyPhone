package com.daerobotics.securemyphone


import android.Manifest
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

import android.support.v7.app.AppCompatActivity
import android.support.v4.app.ActivityCompat

class MainActivity : Activity() {

    lateinit var myIntent: Intent


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Example of a call to a native method
        sample_text.text = stringFromJNI()
        askPermissions()
        myIntent = Intent(this, MyService::class.java)
        val componentName = startService(myIntent)
        if (componentName != null) {
            sample_text.text = "Already running!"
        }
    }

    public fun onBtnStop(v: View) {
        sample_text.text = "Stopped"

        var i: Intent = Intent()
        i.action = CommandReceiver.ACTION_STOP
        //sendBroadcast(i)
        stopService(myIntent)

    }

    public fun onBtnStart(v: View) {
        val componentName = startService(myIntent)
        sample_text.text = "Started"
        if (componentName != null) {
            sample_text.text = "Already running!"
        }

    }


    private fun askPermissions() {
        val lstPermissions = arrayOf(Manifest.permission.CAMERA, Manifest.permission.ACCESS_FINE_LOCATION)
        ActivityCompat.requestPermissions(this, lstPermissions, 0)
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    companion object {

        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
        }
    }
}
