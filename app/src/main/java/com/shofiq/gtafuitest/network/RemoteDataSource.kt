package com.shofiq.gtafuitest.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.shofiq.gtafuitest.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDataSource {
    companion object {
        const val OWNER = "flutter"
        const val REPO = "flutter"
        const val USER = "mikedewar"
        const val BASE_URL = " https://api.github.com"
    }
    private var interceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY);
    private val okHttpClient = OkHttpClient.Builder().also {
        if (BuildConfig.DEBUG) {
            it.addInterceptor(interceptor)
        }
    }.build()

    fun <Api> buildApi(
        api: Class<Api>
    ): Api {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api)
    }
}