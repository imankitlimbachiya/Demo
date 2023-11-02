package com.app.qfonapp.adapter

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.app.qfonapp.R
import com.app.qfonapp.databinding.ItemAddOptionBinding
import com.app.qfonapp.interfaces.AddPollInterface
import com.app.qfonapp.model.PollOption
import com.app.qfonapp.util.Config

class AddOptionAdapter(
    var activity: FragmentActivity?,
    private var optionList: MutableList<PollOption>,
    private var addPollInterface: AddPollInterface
) : RecyclerView.Adapter<AddOptionAdapter.AddOptionBinding>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddOptionBinding {
        return AddOptionBinding(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.item_add_option, parent, Config.doNot_
            )
        )
    }

    override fun onBindViewHolder(holder: AddOptionBinding, position: Int) {
        // val model: PollOption = optionList[position]
        if (position == optionList.size - 1) {
            holder.binding.edtOption.imeOptions = EditorInfo.IME_ACTION_DONE
        } else holder.binding.edtOption.imeOptions = EditorInfo.IME_ACTION_NEXT
        holder.binding.imgDelete.setOnClickListener {
            addPollInterface.onDelete(position)
        }
        holder.binding.edtOption.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(editable: Editable) {
                addPollInterface.afterTextChanged(editable.toString(), holder.adapterPosition)
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })
    }

    override fun getItemCount(): Int {
        return optionList.size
    }

    class AddOptionBinding(var binding: ItemAddOptionBinding) : RecyclerView.ViewHolder(
        binding.root
    )
}