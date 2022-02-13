package com.shofiq.gtafuitest.utils

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import coil.load
import coil.transform.CircleCropTransformation
import com.google.android.material.snackbar.Snackbar
import com.shofiq.gtafuitest.network.Resource

fun View.visible(isVisible: Boolean){
    visibility = if (isVisible) View.VISIBLE else View.GONE
}
fun View.enable(enabled: Boolean){
    isEnabled = enabled
}
fun View.snackbar(message: String, action: (() -> Unit)? = null){
    val snackbar = Snackbar.make(this, message, Snackbar.LENGTH_LONG)
    action?.let {
        snackbar.setAction("Retry"){
            it()
        }
    }
    snackbar.show()
}

fun Fragment.handleApiError(
    failure: Resource.Failure,
    retry: (() -> Unit)? = null
){
    when{
        failure.isNetworkError -> requireView().snackbar("Please check your internet connection", retry)
        else -> {
            val errorBody = failure.errorBody!!.string().toString()
            requireView().snackbar(errorBody)
        }
    }
}

@BindingAdapter("loadUserPhoto")
fun setPlayerImage(imageView: ImageView, path: String) {
    imageView.load(path){
        crossfade(true)
        crossfade(500)
        transformations(CircleCropTransformation())
    }
}
