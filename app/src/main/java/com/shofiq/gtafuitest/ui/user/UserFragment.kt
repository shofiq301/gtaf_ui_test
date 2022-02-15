package com.shofiq.gtafuitest.ui.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.shofiq.gtafuitest.base.BaseFragment
import com.shofiq.gtafuitest.databinding.UserFragmentBinding
import com.shofiq.gtafuitest.network.ApiInterface
import com.shofiq.gtafuitest.network.RemoteDataSource.Companion.USER
import com.shofiq.gtafuitest.network.Resource
import com.shofiq.gtafuitest.repository.ProfileRepository
import com.shofiq.gtafuitest.utils.handleApiError
import com.shofiq.gtafuitest.utils.visible
import com.shofiq.gtafuitest.viewmodels.UserViewModel
import kotlinx.coroutines.launch

class UserFragment : BaseFragment<UserFragmentBinding, UserViewModel, ProfileRepository>() {
    override fun getViewModel() = UserViewModel::class.java

    override fun getFragmentBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?
    ) = UserFragmentBinding.inflate(layoutInflater, container, false)

    override fun getRepository() =
        ProfileRepository(remoteDataSource.buildApi(ApiInterface::class.java))

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            progressLayout.spinKit.visible(true)
            binding.loadingState = false
        }
        viewModel.getPublicProfile(USER)
        viewModel.profileData.observe(viewLifecycleOwner) {
            with(binding) { progressLayout.spinKit.visible(it is Resource.Loading) }
            when (it) {
                is Resource.Success ->
                    lifecycleScope.launch {
                        binding.profile = it.value
                        binding.loadingState = true
                    }
                is Resource.Loading -> {}
                else ->
                    handleApiError(it as Resource.Failure)
            }
        }
    }
}