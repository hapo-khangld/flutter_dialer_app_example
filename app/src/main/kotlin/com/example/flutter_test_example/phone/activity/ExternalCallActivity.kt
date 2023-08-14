package com.example.flutter_test_example.phone.activity

import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.os.PowerManager
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.getSystemService

class ExternalCallActivity : AppCompatActivity(), ActivityCompat.OnRequestPermissionsResultCallback {
    companion object {
        //private const val ANIMATION_DURATION = 250L
        fun getStartIntent(context: Context): Intent {
            val openAppIntent = Intent(context, ExternalCallActivity::class.java)
            openAppIntent.flags = Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
            return openAppIntent
        }
    }

    private val TAG: String = ExternalCallActivity::class.java.name

//    private lateinit var binding: ActivityExternalCallBinding
//    private val callsViewModel: ExternalCallsViewModel by viewModels()
//    private val externalControlsViewModel: ExternalCallsControlsViewModel by viewModels()
//    private val audioManager: AudioManager get() = getSystemService(Context.AUDIO_SERVICE) as AudioManager
    private var proximityWakeLock: PowerManager.WakeLock? = null

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        Log.i(TAG, "onRequestPermissionsResult triggered.")
        Log.i(TAG, "Request code is: $requestCode.")
    }
}