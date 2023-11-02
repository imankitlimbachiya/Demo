package com.app.qfonapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.qfonapp.R
import com.app.qfonapp.databinding.ItemPollOptionBinding
import com.app.qfonapp.interfaces.PollInterface
import com.app.qfonapp.model.Option
import com.app.qfonapp.util.Config

class PollOptionAdapter(
    private var optionList: MutableList<Option>,
    private var pollInterface: PollInterface? = null,
    private var isAttempted: Boolean
) : RecyclerView.Adapter<PollOptionAdapter.PollOptionBinding>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PollOptionBinding {
        return PollOptionBinding(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_poll_option,
                parent,
                Config.doNot_
            )
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: PollOptionBinding, position: Int) {
        val model: Option = optionList[position]
        holder.binding.txtPollTitle.text = model.pollOption

        if (!isAttempted) {
            holder.binding.imgCircle.setOnClickListener {
                pollInterface!!.onOptionSelect(model.pollId!!, model.pollOptionId!!)
                holder.binding.imgCircle.setImageResource(R.drawable.ic_circle_check)
                holder.binding.ProgressBar.progress = 100
                holder.binding.txtPercentage.text = "100%"
            }
        } else {
            if (model.pollAttempted == true) {
                holder.binding.imgCircle.setImageResource(R.drawable.ic_circle_check)
                holder.binding.ProgressBar.progress = 100
                holder.binding.txtPercentage.text = "100%"
            }
        }
    }

    override fun getItemCount(): Int {
        return optionList.size
    }

    class PollOptionBinding(var binding: ItemPollOptionBinding) : RecyclerView.ViewHolder(
        binding.root
    )
}