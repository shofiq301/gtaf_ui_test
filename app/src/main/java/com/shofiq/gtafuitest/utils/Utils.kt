@file:Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")

package com.shofiq.gtafuitest.utils

import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import coil.load
import coil.transform.CircleCropTransformation
import com.google.android.material.snackbar.Snackbar
import com.shofiq.gtafuitest.network.Resource
import java.text.SimpleDateFormat
import java.util.*

fun View.visible(isVisible: Boolean){
    visibility = if (isVisible) View.VISIBLE else View.GONE
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
        failure.errorCode == 401 ->  requireView().snackbar("Unauthorized, access denied")
        failure.errorCode == 403 ->  requireView().snackbar("Forbidden, undefined error")
        else -> {
            val errorBody = failure.errorBody!!.string()
            requireView().snackbar(errorBody)
        }
    }
}

@BindingAdapter("loadUserPhoto")
fun setUserImage(imageView: ImageView, path: String?) {
    imageView.load(path){
        crossfade(true)
        crossfade(500)
        transformations(CircleCropTransformation())
    }
}

@SuppressLint("SimpleDateFormat")
@BindingAdapter("formattedDate")
fun toPrettyDate(textView: TextView, dateStr: String) {
    val nowTime = Calendar.getInstance()
    val neededTime = Calendar.getInstance()
    val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
    neededTime.time = sdf.parse(dateStr)
    val date = sdf.parse(dateStr)
    textView.text = when {
        nowTime[Calendar.DATE] == neededTime[Calendar.DATE] -> {
            SimpleDateFormat("HH:mm", Locale.getDefault()).format(date!!)
        }
        nowTime[Calendar.DATE] - neededTime[Calendar.DATE] == 1  -> {
            "Yesterday"
        }
        nowTime[Calendar.DATE] - neededTime[Calendar.DATE] in 2..6 -> {
            SimpleDateFormat("EEEE", Locale.getDefault()).format(date!!)
        }
        else -> {
            SimpleDateFormat("MM/dd/yy", Locale.getDefault()).format(date!!)
        }
    }

}
