package com.shofiq.gtafuitest.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shofiq.gtafuitest.models.ProfileResponse
import com.shofiq.gtafuitest.network.Resource
import com.shofiq.gtafuitest.repository.ProfileRepository
import kotlinx.coroutines.launch

class UserViewModel(
    private val repository: ProfileRepository
) : ViewModel() {
    private val _profileResponse = MutableLiveData<Resource<ProfileResponse>>()
    val profileData: LiveData<Resource<ProfileResponse>>
    get() = _profileResponse

    fun getPublicProfile(name: String) = viewModelScope.launch {
        _profileResponse.value = Resource.Loading
        _profileResponse.value = repository.fetchProfile(name)
    }
}