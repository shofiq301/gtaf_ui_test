package com.shofiq.gtafuitest.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shofiq.gtafuitest.repository.CommitRepository
import com.shofiq.gtafuitest.ui.commit.CommitViewModel
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(
    private val repository: BaseRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(CommitViewModel::class.java) -> CommitViewModel(repository as CommitRepository) as T
            else -> throw IllegalArgumentException("ViewModel class not found")
        }
    }
}