package com.shofiq.gtafuitest.repository

import com.shofiq.gtafuitest.base.BaseRepository
import com.shofiq.gtafuitest.network.ApiInterface
import retrofit2.http.Path
import retrofit2.http.Query

class CommitRepository(
    private val  api: ApiInterface
): BaseRepository() {
    suspend fun fetchCommit(owner: String,
                    repo: String,
                    page: Int,
                    per_page: Int
    ) = safeApiCall { api.getCommitList(owner, repo,page, per_page) }
}