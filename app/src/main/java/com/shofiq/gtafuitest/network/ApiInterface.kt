package com.shofiq.gtafuitest.network

import com.shofiq.gtafuitest.models.CommitResponse
import com.shofiq.gtafuitest.models.ProfileResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
interface ApiInterface {

    @GET("/repos/{owner}/{repo}/commits")
    suspend fun getCommitList(@Path("owner") owner: String,
                      @Path("repo") repo: String,
                      @Query("page") page: Int,
                      @Query("per_page") per_page: Int): CommitResponse

    @GET("/users/{name}")
    suspend fun getPublicProfile(@Path("name") name: String): ProfileResponse
}