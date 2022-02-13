package com.shofiq.gtafuitest.ui.commit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shofiq.gtafuitest.models.CommitResponse
import com.shofiq.gtafuitest.network.Resource
import com.shofiq.gtafuitest.repository.CommitRepository
import kotlinx.coroutines.launch

class CommitViewModel(
    private val repository: CommitRepository
) : ViewModel() {

    private val _commitResponse: MutableLiveData<Resource<CommitResponse>> = MutableLiveData()
    val commitData: LiveData<Resource<CommitResponse>>
        get() = _commitResponse

    fun getCommitList(
        owner: String,
        repo: String,
        page: Int,
        per_page: Int
    ) = viewModelScope.launch {
        _commitResponse.value = Resource.Loading
        _commitResponse.value = repository.fetchCommit(owner, repo, page, per_page)
    }
}