package com.shofiq.gtafuitest.ui.commit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.shofiq.gtafuitest.databinding.CommitItemBinding
import com.shofiq.gtafuitest.models.CommitResponseItem
import com.shofiq.gtafuitest.ui.commit.RecyclerViewAdapter.MyViewHolder
import com.shofiq.gtafuitest.utils.MyDiffUtil

class RecyclerViewAdapter : RecyclerView.Adapter<MyViewHolder>() {
    private var commitLiast = emptyList<CommitResponseItem>()

    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    class MyViewHolder(private val binding: CommitItemBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(commit: CommitResponseItem) {
            binding.commitItem = commit
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            CommitItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val commitItem = commitLiast[position]
        holder.bind(commitItem)
    }

    override fun getItemCount(): Int {
        return commitLiast.size
    }

    fun setData(newList: List<CommitResponseItem>) {
        val diffUtil = MyDiffUtil(commitLiast, newList)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        commitLiast = newList
        diffResult.dispatchUpdatesTo(this)
    }
}