package com.scrrrt.kompoze.data.dto.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Cat(
    val url: String,
    val width: Int,
    val height: Int
)