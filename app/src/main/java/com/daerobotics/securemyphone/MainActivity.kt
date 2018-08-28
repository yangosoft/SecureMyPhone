package com.daerobotics.securemyphone

import android.Manifest
import android.app.Activity
import android.os.Bundle
import android.view.View

import kotlinx.android.synthetic.main.activity_main.*

import android.content.Intent

class MainActivity : Activity() {

    lateinit var myIntent: Intent


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Example of a call to a native method
        sample_text.text = stringFromJNI()

        myIntent = Intent(this, MyService::class.java)
        val componentName = startService(myIntent)
        if( componentName != null )
        {
            sample_text.text = "Already running!"
        }
    }

    public fun onBtnStop(v: View)
    {
        sample_text.text = "Stopped"
        stopService(myIntent)
    }

    public fun onBtnStart(v: View)
    {
        val componentName = startService(myIntent)
        sample_text.text = "Started"
        if( componentName != null )
        {
            sample_text.text = "Already running!"
        }

    }


    private fun askPermissions()
    {

                // No explanation needed, we can request the permission.
        //val requestPermissions: Any = ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), MY_PERMISSIONS_REQUEST_READ_CONTACTS)


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
