package com.example.flutter_test_example

import android.annotation.SuppressLint
import android.app.role.RoleManager
import android.content.Context
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.os.Build
import android.telecom.TelecomManager
import android.telephony.TelephonyManager
import androidx.annotation.RequiresApi
import io.flutter.embedding.android.FlutterFragmentActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel

class MainActivity: FlutterFragmentActivity() {
    private val CHANNEL = "example.com/native_channel"
    private val REQUEST_ID = 1

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)

        val methodChannel = MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL)
        methodChannel.setMethodCallHandler { call, result ->
            when (call.method) {
                "requestDialerRole" -> requestDialerRole(result)
                else -> result.notImplemented()
            }
        }
    }

    @SuppressLint("QueryPermissionsNeeded")
    @RequiresApi(Build.VERSION_CODES.Q)
    private fun requestDialerRole(result: MethodChannel.Result) {
        val roleManager = getSystemService(Context.ROLE_SERVICE) as RoleManager

        if (roleManager.isRoleAvailable(RoleManager.ROLE_DIALER)) {
            val hasRole = roleManager.isRoleHeld(RoleManager.ROLE_DIALER)
            if (!hasRole) {
                val intent = roleManager.createRequestRoleIntent(RoleManager.ROLE_DIALER)
                val resolveInfos: List<ResolveInfo> = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY)
                if (resolveInfos.isNotEmpty()) {
                    startActivityForResult(intent, REQUEST_ID)
                    result.success(true)
                } else {
                    result.error("NoActivity", "No activity found to handle the request.", null)
                }
            } else {
                result.success(true)
            }
        } else {
            result.error("RoleUnavailable", "Dialer role is not available on this device.", null)
        }
    }
}
