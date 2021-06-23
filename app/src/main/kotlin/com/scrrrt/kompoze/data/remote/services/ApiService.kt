package com.scrrrt.kompoze.data.remote.services

import com.scrrrt.kompoze.data.dto.error.ApiError
import com.scrrrt.kompoze.data.dto.request.SubmitFormRequest
import com.scrrrt.kompoze.data.dto.response.Cat
import com.scrrrt.kompoze.data.dto.response.OtpResponse
import com.scrrrt.kompoze.data.remote.apiadapter.NetworkResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET("images/search")
    suspend fun getCats(): GenericResponse<List<Cat>>

    @POST("submit/form")
    suspend fun submitData(@Body request: SubmitFormRequest): GenericResponse<OtpResponse>
}

typealias GenericResponse<S> = NetworkResponse<S, ApiError>