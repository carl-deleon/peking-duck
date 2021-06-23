package com.scrrrt.kompoze.data.remote.interceptor

import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

object ApiInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request().url.toUri().toString()
        val responseString = when {
            url.endsWith("form") -> otpResponseJson.trimIndent()
            else -> ""
        }

        return if (responseString.isEmpty()) {
            chain.proceed(chain.request())
        } else {
            chain.proceed(chain.request())
                .newBuilder()
                .code(200)
                .protocol(Protocol.HTTP_2)
                .message(responseString)
                .body(
                    responseString
                        .toByteArray()
                        .toResponseBody("application/json".toMediaType())
                )
                .addHeader("content-type", "application/json")
                .build()
        }
    }

    private const val otpResponseJson =
        """{"id": "1","request_id": "1234567890","grant_type": "ft_otp","otp_type": "1"}"""
}