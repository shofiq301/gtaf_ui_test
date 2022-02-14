package com.shofiq.gtafuitest.ui.commit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.shofiq.gtafuitest.base.BaseFragment
import com.shofiq.gtafuitest.databinding.CommitFragmentBinding
import com.shofiq.gtafuitest.network.ApiInterface
import com.shofiq.gtafuitest.network.RemoteDataSource.Companion.OWNER
import com.shofiq.gtafuitest.network.RemoteDataSource.Companion.REPO
import com.shofiq.gtafuitest.network.Resource
import com.shofiq.gtafuitest.repository.CommitRepository
import com.shofiq.gtafuitest.utils.handleApiError
import com.shofiq.gtafuitest.utils.visible
import com.shofiq.gtafuitest.viewmodels.CommitViewModel
import kotlinx.coroutines.launch

class CommitFragment : BaseFragment<CommitFragmentBinding, CommitViewModel, CommitRepository>() {

    private val myAdapter by lazy { RecyclerViewAdapter() }
    override fun getViewModel() = CommitViewModel::class.java

    override fun getFragmentBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?
    ) = CommitFragmentBinding.inflate(layoutInflater, container, false)

    override fun getRepository() = CommitRepository(remoteDataSource.buildApi(ApiInterface::class.java))


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.progressLayout.spinKit.visible(true)
        binding.recyclerCommitList.adapter = myAdapter
        viewModel.getCommitList(OWNER, REPO, 1, 10)
        viewModel.commitData.observe(viewLifecycleOwner) {
            binding.progressLayout.spinKit.visible(it is Resource.Loading)
            when (it) {
                is Resource.Success -> {
                    lifecycleScope.launch {
                        myAdapter.setData(it.value)
                    }
                }
                is Resource.Loading -> {}
                else -> {
                    handleApiError(it as Resource.Failure)
                }
            }
        }
    }
}