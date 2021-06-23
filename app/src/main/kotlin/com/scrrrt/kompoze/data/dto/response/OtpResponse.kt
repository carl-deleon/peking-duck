package com.scrrrt.kompoze.data.dto.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OtpResponse(
    val id: String,
    @Json(name = "request_id") val requestId: String,
    @Json(name = "grant_type") val grantType: String,
    @Json(name = "otp_type") val otpType: OtpType,
    @Json(name = "mobile_number") val mobileNumber: String? = null
)

enum class OtpType {

    SMS,
    TOTP
}