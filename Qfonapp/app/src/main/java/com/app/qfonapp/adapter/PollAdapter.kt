package com.app.qfonapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.qfonapp.R
import com.app.qfonapp.databinding.ItemPollBinding
import com.app.qfonapp.interfaces.PollInterface
import com.app.qfonapp.model.Poll
import com.app.qfonapp.util.Config

class PollAdapter(
    private var pollList: MutableList<Poll>,
    private var pollInterface: PollInterface? = null,
    private var isAttempted: Boolean
) : RecyclerView.Adapter<PollAdapter.PollBinding>() {

    private var adapter: PollOptionAdapter? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PollBinding {
        return PollBinding(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.item_poll, parent, Config.doNot_
            )
        )
    }

    override fun onBindViewHolder(holder: PollBinding, position: Int) {
        val model: Poll = pollList[position]
        holder.binding.txtPollTitle.text = model.pollTitle

        adapter = PollOptionAdapter(model.option, pollInterface, isAttempted)
        holder.binding.rvOption.adapter = adapter
    }

    override fun getItemCount(): Int {
        return pollList.size
    }

    class PollBinding(var binding: ItemPollBinding) : RecyclerView.ViewHolder(
        binding.root
    )
}