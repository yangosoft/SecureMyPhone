package com.daerobotics.securemyphone

import android.app.Activity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

import android.content.Intent

class MainActivity : Activity() {

    private val myIntent = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Example of a call to a native method
        sample_text.text = stringFromJNI()

        val intent = Intent(this, MyService::class.java)
        startService(intent)
    }

    public fun onBtnStop(v: View)
    {
        sample_text.text = "Stopped"
        val intent = Intent(this, MyService::class.java)
        stopService(intent)
    }

    public fun onBtnStart(v: View)
    {
        val intent = Intent(this, MyService::class.java)
        startService(intent)
        sample_text.text = "Started"
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
