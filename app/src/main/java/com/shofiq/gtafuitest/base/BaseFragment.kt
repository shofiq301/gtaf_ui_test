package com.shofiq.gtafuitest.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.shofiq.gtafuitest.network.RemoteDataSource

abstract class BaseFragment<VB: ViewBinding, VM: ViewModel, R: BaseRepository> : Fragment() {

    lateinit var binding: VB
    val remoteDataSource = RemoteDataSource()
    lateinit var viewModel: VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = getFragmentBinding(layoutInflater, container)
        val factory = ViewModelFactory(getRepository())
        viewModel = ViewModelProvider(this, factory)[getViewModel()]
        return binding.root
    }

    abstract fun getViewModel(): Class<VM>

    abstract fun getFragmentBinding(layoutInflater: LayoutInflater, container: ViewGroup?): VB

    abstract fun getRepository(): R

}