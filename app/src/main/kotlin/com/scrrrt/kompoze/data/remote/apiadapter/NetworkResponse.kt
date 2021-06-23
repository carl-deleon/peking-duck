package com.scrrrt.kompoze.data.remote.apiadapter

import com.scrrrt.kompoze.data.dto.response.OtpResponse
import java.io.IOException

sealed class NetworkResponse<out T : Any, out U : Any> {

    data class Success<T : Any>(val body: T) : NetworkResponse<T, Nothing>()

    data class ApiError<U : Any>(val body: U, val code: Int) : NetworkResponse<Nothing, U>()

    data class NetworkError(val error: IOException) : NetworkResponse<Nothing, Nothing>()

    data class UnknownError(val error: Throwable?) : NetworkResponse<Nothing, Nothing>()

    data class OtpRequired(val otpResponse: OtpResponse?) : NetworkResponse<Nothing, Nothing>()
}
