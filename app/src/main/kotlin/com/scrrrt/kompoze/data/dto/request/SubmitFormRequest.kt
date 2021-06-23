package com.scrrrt.kompoze.data.dto.request

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SubmitFormRequest(
    val name: String,
    val age: Int
)