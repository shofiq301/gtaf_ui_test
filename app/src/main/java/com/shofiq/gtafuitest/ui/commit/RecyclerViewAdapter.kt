package com.shofiq.gtafuitest.ui.commit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.shofiq.gtafuitest.databinding.CommitItemBinding
import com.shofiq.gtafuitest.models.CommitResponseItem
import com.shofiq.gtafuitest.ui.commit.RecyclerViewAdapter.MyViewHolder
import com.shofiq.gtafuitest.utils.MyDiffUtil
import java.text.SimpleDateFormat
import java.util.*

class RecyclerViewAdapter: RecyclerView.Adapter<MyViewHolder>() {
    private var commitLiast = emptyList<CommitResponseItem>()
    class MyViewHolder(private val binding: CommitItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(commit: CommitResponseItem){
            binding.commitItem = commit
            val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
            val date = sdf.parse(commit.commit.author.date)
            binding.txtTime.text = toPrettyDate(date)
        }
        private fun toPrettyDate(date: Date): String {
            val nowTime = Calendar.getInstance()
            val neededTime = Calendar.getInstance()
            nowTime.time = date

            return if (neededTime[Calendar.YEAR] == nowTime[Calendar.YEAR]) {
                if (neededTime[Calendar.WEEK_OF_MONTH] == nowTime[Calendar.WEEK_OF_MONTH]) {
                    when {
                        nowTime[Calendar.DATE] == neededTime[Calendar.DATE] -> {
                            SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date(this))
                        }
                        nowTime[Calendar.DATE] - neededTime[Calendar.DATE] == 1 -> {
                            "Yesterday"
                        }
                        nowTime[Calendar.DATE] - neededTime[Calendar.DATE] in 2..6 -> {
                            SimpleDateFormat("EEEE", Locale.getDefault()).format(Date(this))
                        }
                        else -> {
                            SimpleDateFormat(" MM/dd/yy", Locale.getDefault()).format(Date(this))
                        }
                    }
                } else {
                    SimpleDateFormat(" MM/dd/yy", Locale.getDefault()).format(Date(this))
                }
            } else {
                SimpleDateFormat(" MM/dd/yy", Locale.getDefault()).format(Date(this))
            }
        }
        private fun Date(holder: MyViewHolder): Date {
            return Calendar.getInstance().time
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(CommitItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val commitItem = commitLiast[position]
        holder.bind(commitItem)
    }

    override fun getItemCount(): Int {
        return commitLiast.size
    }

    fun setData(newList: List<CommitResponseItem>){
        val diffUtil = MyDiffUtil(commitLiast, newList)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        commitLiast = newList
        diffResult.dispatchUpdatesTo(this)
    }
}