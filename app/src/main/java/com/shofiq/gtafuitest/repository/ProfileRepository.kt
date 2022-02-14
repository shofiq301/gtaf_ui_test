package com.shofiq.gtafuitest.repository

import com.shofiq.gtafuitest.base.BaseRepository
import com.shofiq.gtafuitest.network.ApiInterface
class  ProfileRepository(
    private val  api: ApiInterface
): BaseRepository(){
    suspend fun fetchProfile(name: String) = safeApiCall {
        api.getPublicProfile(name)
    }
}