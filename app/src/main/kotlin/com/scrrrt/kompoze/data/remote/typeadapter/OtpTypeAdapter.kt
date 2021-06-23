package com.scrrrt.kompoze.data.remote.typeadapter

import com.scrrrt.kompoze.data.dto.response.OtpType
import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonDataException

object OtpTypeAdapter {

    @FromJson
    fun fromJson(otpType: String): OtpType {
        return when (otpType.toInt()) {
            1 -> OtpType.SMS
            2 -> OtpType.TOTP
            else -> throw JsonDataException("Unknown type: $otpType")
        }
    }
}