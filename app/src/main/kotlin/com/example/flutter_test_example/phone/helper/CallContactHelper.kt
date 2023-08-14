package com.example.flutter_test_example.phone.helper

import android.content.Context
import android.net.Uri
import android.os.Looper
import android.telecom.Call
import com.example.flutter_test_example.R
import com.example.flutter_test_example.phone.extensions.getMyContactsCursor
import com.example.flutter_test_example.phone.extensions.isConference
import com.example.flutter_test_example.phone.models.CallContact

fun getCallContact(context: Context, call: Call?, callback: (CallContact) -> Unit) {
    if (call.isConference()) {
        callback(CallContact(context.getString(R.string.conference), "", "", ""))
        return
    }
    val privateCursor = context.getMyContactsCursor(
        favoritesOnly = false,
        withPhoneNumbersOnly = true
    )

    ensureBackgroundThread {
        val callContact = CallContact("", "", "", "")

        val handle = try {
            call?.details?.handle?.toString()
        } catch (e: NullPointerException) {
            null
        }

        if (handle == null) {
            callback(callContact)
            return@ensureBackgroundThread
        }

        val uri = Uri.decode(handle)

    }
}


fun isOnMainThread() = Looper.myLooper() == Looper.getMainLooper()

fun ensureBackgroundThread(callback: () -> Unit) {
    if (isOnMainThread()) {
        Thread {
            callback()
        }.start()
    } else {
        callback()
    }
}