package com.example.flutter_test_example.phone.models

import android.telephony.PhoneNumberUtils

// model used at displaying recent calls, for contacts with multiple numbers specifify the number and type
data class RecentCall(
    var id: Int, var phoneNumber: String, var name: String, var photoUri: String, var startTS: Int, var duration: Int, var type: Int,
    var neighbourIDs: ArrayList<Int>, val simID: Int, var specificNumber: String, var specificType: String
) {
    /*
    fun doesContainPhoneNumber(text: String): Boolean {
        val normalizedText = text.normalizePhoneNumber()
        return PhoneNumberUtils.compare(phoneNumber.normalizePhoneNumber(), normalizedText) ||
            phoneNumber.contains(text) ||
            phoneNumber.normalizePhoneNumber().contains(normalizedText) ||
            phoneNumber.contains(normalizedText)
    }
     */
}
