package com.shofiq.gtafuitest.base

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.shofiq.gtafuitest.R
import com.shofiq.gtafuitest.network.RemoteDataSource

abstract class BaseFragment<VB: ViewBinding, VM: ViewModel, R: BaseRepository> : Fragment() {

    private lateinit var binding: VB
    val remoteDataSource = RemoteDataSource()
    lateinit var viewModel: VM
    var progressBuilder: AlertDialog.Builder? = null
    var dialog: AlertDialog? = null

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


//    open fun createDialog(context: Context) {
//        progressBuilder = AlertDialog.Builder(context, R.style.ProgressStyle)
//        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//        @SuppressLint("InflateParams") val view: View =
//            inflater.inflate(R.layout.dialog_progress, null)
//        progressBuilder!!.setView(view)
//        dialog = progressBuilder!!.create()
//        dialog!!.setCancelable(false)
//        dialog!!.setMessage("Please wait..")
//    }
//
//    open fun showProgress() {
//        try {
//            if (dialog != null)
//                dialog?.show()
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//    }
//
//    open fun hideProgress() {
//        try {
//            if (dialog != null && dialog!!.isShowing) {
//                dialog!!.dismiss()
//            }
//        } catch (e: java.lang.Exception) {
//            e.printStackTrace()
//        }
//    }

}