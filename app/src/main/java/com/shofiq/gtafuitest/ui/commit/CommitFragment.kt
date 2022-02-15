package com.shofiq.gtafuitest.ui.commit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.shofiq.gtafuitest.base.BaseFragment
import com.shofiq.gtafuitest.databinding.CommitFragmentBinding
import com.shofiq.gtafuitest.network.ApiInterface
import com.shofiq.gtafuitest.network.RemoteDataSource.Companion.OWNER
import com.shofiq.gtafuitest.network.RemoteDataSource.Companion.REPO
import com.shofiq.gtafuitest.network.Resource
import com.shofiq.gtafuitest.repository.CommitRepository
import com.shofiq.gtafuitest.utils.handleApiError
import com.shofiq.gtafuitest.viewmodels.CommitViewModel
import kotlinx.coroutines.launch

class CommitFragment : BaseFragment<CommitFragmentBinding, CommitViewModel, CommitRepository>() , SwipeRefreshLayout.OnRefreshListener {

    private val myAdapter by lazy { RecyclerViewAdapter() }
    override fun getViewModel() = CommitViewModel::class.java

    override fun getFragmentBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?
    ) = CommitFragmentBinding.inflate(layoutInflater, container, false)

    override fun getRepository() = CommitRepository(remoteDataSource.buildApi(ApiInterface::class.java))


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myAdapter.also { binding.recyclerCommitList.adapter = it }
        binding.refreshMain.isRefreshing = false
        binding.refreshMain.setOnRefreshListener(this)
        callApi()
        viewModel.commitData.observe(viewLifecycleOwner) {
            with(binding) { refreshMain.isRefreshing = it is Resource.Loading }
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

    private fun callApi(){
        viewModel.getCommitList(OWNER, REPO, 1, 10)
    }

    override fun onRefresh() {
        binding.refreshMain.isRefreshing = true
        callApi()
    }
}