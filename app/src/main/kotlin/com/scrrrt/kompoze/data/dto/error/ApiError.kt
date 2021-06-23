package com.scrrrt.kompoze.data.dto.error

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiError(
    val code: Int,
    val message: String
)