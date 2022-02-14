package com.shofiq.gtafuitest.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shofiq.gtafuitest.repository.CommitRepository
import com.shofiq.gtafuitest.repository.ProfileRepository
import com.shofiq.gtafuitest.viewmodels.CommitViewModel
import com.shofiq.gtafuitest.viewmodels.UserViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(
    private val repository: BaseRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(CommitViewModel::class.java) -> CommitViewModel(repository as CommitRepository) as T
            modelClass.isAssignableFrom(UserViewModel::class.java) -> UserViewModel(repository as ProfileRepository) as T
            else -> throw IllegalArgumentException("ViewModel class not found")
        }
    }
}