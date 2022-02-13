package com.shofiq.gtafuitest.utils

import androidx.recyclerview.widget.DiffUtil
import com.shofiq.gtafuitest.models.CommitResponseItem

class MyDiffUtil(
    private val oldList: List<CommitResponseItem>,
    private val newList: List<CommitResponseItem>
): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].nodeId == newList[newItemPosition].nodeId
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when{
            oldList[oldItemPosition].nodeId != newList[newItemPosition].nodeId ->{
                false
            }
            oldList[oldItemPosition].sha != newList[newItemPosition].sha ->{
                false
            }
            else -> true
        }
    }
}