package com.example.flutter_test_example.phone.models

import android.telecom.PhoneAccountHandle

data class SIMAccount(val id: Int, val handle: PhoneAccountHandle, val label: String, val phoneNumber: String)
