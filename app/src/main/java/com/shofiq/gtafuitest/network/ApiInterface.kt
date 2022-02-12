package com.shofiq.gtafuitest.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.shofiq.gtafuitest.models.CommitResponse
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


const val owner = "shofiq301"
const val repo = "my_player"
const val baseurl = " https://api.github.com"
interface ApiInterface {

    @GET("/repos/{owner}/{repo}/commits")
    suspend fun getCommitList(@Path("owner") owner: String,
                      @Path("repo") repo: String,
                      @Query("page") page: Int,
                      @Query("per_page") per_page: Int): CommitResponse

//    companion object{
//        operator fun invoke(): ApiInterface {
//            val requestInterceptor = Interceptor{
//                val myUrl = it.request()
//                    .url
//                    .newBuilder()
//                    .build()
//                val request = it.request()
//                    .newBuilder()
//                    .url(myUrl)
//                    .build()
//                return@Interceptor it.proceed(request)
//            }
//            val okHttpClient = OkHttpClient.Builder()
//                .addInterceptor(requestInterceptor)
//                .build()
//
//            return Retrofit.Builder()
//                .client(okHttpClient)
//                .baseUrl(baseurl)
//                .addCallAdapterFactory(CoroutineCallAdapterFactory())
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//                .create(ApiInterface::class.java)
//        }
//    }
}