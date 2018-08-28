package com.daerobotics.securemyphone

import android.app.IntentService
import android.content.Intent
import android.hardware.Camera
import android.util.Log
import android.content.Context
import android.content.pm.PackageManager
import android.os.Parcel
import android.os.Parcelable


@Suppress("DEPRECATION")
class MyService() : IntentService("MyIntentService") , Camera.PictureCallback {
    override fun onPictureTaken(p0: ByteArray?, p1: Camera?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        Log.i(TAG, "Photo ok!")

    }

    private val TAG = "ServiceExample"

    override fun onHandleIntent(arg0: Intent?) {
        Log.i(TAG, "Intent Service started")
        takeSelfie()

    }


    /** Check if this device has a camera */
    private fun checkCameraHardware(context: Context): Boolean {
        if (context.packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            // this device has a camera
            return true
        } else {
            // no camera on this device
            return false
        }
    }

    private fun takeSelfie(){

        if(false === checkCameraHardware( this.applicationContext ))
        {
            Log.e(TAG, "No cameras in this device")
            return
        }

        var cam = openCamera(Camera.CameraInfo.CAMERA_FACING_FRONT)
        cam?.lock()
        cam?.takePicture(null,null,this)

        cam?.unlock()

    }

    private fun openCamera(cameraType: Int): Camera? {
        var cameraCount = 0
        var cam: Camera? = null
        val cameraInfo = Camera.CameraInfo()
        cameraCount = Camera.getNumberOfCameras()
        for (camIdx in 0 until cameraCount) {
            Camera.getCameraInfo(camIdx, cameraInfo)
            //if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
            if (cameraInfo.facing == cameraType) {
                try {
                    cam = Camera.open(camIdx)
                } catch (e: RuntimeException) {
                    Log.e(TAG, "Camera failed to open: " + e.localizedMessage)
                }

            }
        }

        return cam
    }

}
