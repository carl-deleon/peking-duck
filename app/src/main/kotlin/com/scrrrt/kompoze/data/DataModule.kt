package com.scrrrt.kompoze.data

import com.scrrrt.kompoze.data.remote.apiadapter.NetworkResponseAdapterFactory
import com.scrrrt.kompoze.data.remote.interceptor.ApiInterceptor
import com.scrrrt.kompoze.data.remote.services.ApiService
import com.scrrrt.kompoze.data.remote.typeadapter.OtpTypeAdapter
import com.squareup.moshi.Moshi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import timber.log.Timber

object DataModule {

    fun getApiService(): ApiService = Retrofit.Builder()
        .baseUrl("https://api.thecatapi.com/v1/")
        .client(okHttpClient())
        .addConverterFactory(MoshiConverterFactory.create(moshi()))
        .addCallAdapterFactory(NetworkResponseAdapterFactory(moshi = moshi()))
        .build()
        .create()

    private fun moshi(): Moshi = Moshi.Builder()
        .add(OtpTypeAdapter)
        .build()

    private fun okHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(okHttpLoggingInterceptor())
            .addInterceptor(ApiInterceptor)
            .build()
    }

    private fun okHttpLoggingInterceptor(): Interceptor {
        return HttpLoggingInterceptor { Timber.tag("OkHttp").d(it) }
            .apply { level = HttpLoggingInterceptor.Level.BODY }
    }
}