package com.shofiq.gtafuitest.ui.commit

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.shofiq.gtafuitest.R
import com.shofiq.gtafuitest.base.BaseFragment
import com.shofiq.gtafuitest.base.ViewModelFactory
import com.shofiq.gtafuitest.databinding.CommitFragmentBinding
import com.shofiq.gtafuitest.network.ApiInterface
import com.shofiq.gtafuitest.network.Resource
import com.shofiq.gtafuitest.repository.CommitRepository
import com.shofiq.gtafuitest.ui.user.UserViewModel

class CommitFragment : BaseFragment<CommitFragmentBinding, CommitViewModel, CommitRepository>() {
    override fun getViewModel() = CommitViewModel::class.java

    override fun getFragmentBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?
    ) = CommitFragmentBinding.inflate(layoutInflater, container, false)

    override fun getRepository() = CommitRepository(remoteDataSource.buildApi(ApiInterface::class.java))

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getCommitList("shofiq301", "my_player", 1, 10)
        viewModel.commitData.observe(viewLifecycleOwner, Observer {
            when(it){
                is Resource.Success -> {
                    Log.d("SUCCESS",it.toString())
                }
                is Resource.Failure -> {
                    Log.d("FAILED",it.toString())
                }
            }
        })
    }


}