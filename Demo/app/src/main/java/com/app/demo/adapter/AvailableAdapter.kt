package com.app.demo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.demo.R
import com.app.demo.base.BaseFragment
import com.app.demo.databinding.ItemAvailableBinding
import com.app.demo.interfaces.AvailableInterface
import com.app.demo.model.Available
import com.app.demo.utils.FontDemo

class AvailableAdapter(
    ctx: Context?, data: Available?, cb: AvailableInterface?
) : RecyclerView.Adapter<RecyclerView.ViewHolder?>() {

    private val data: Available?
    private val cb: AvailableInterface?
    private var eFont: FontDemo? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return VhAvailable(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_available, parent, BaseFragment.doNot
            )
        )
    }

    override fun onBindViewHolder(m: RecyclerView.ViewHolder, position: Int) {
        val model = data!!.data.list[position]
        val available: VhAvailable?
        available = m as VhAvailable
        available.b.model = model as Available.Data.List?
    }

    override fun getItemCount(): Int {
        return data!!.data.list.size
    }

    internal class VhAvailable(b: ItemAvailableBinding) : RecyclerView.ViewHolder(b.root) {

        val b: ItemAvailableBinding

        init {
            this.b = b
        }
    }

    init {
        this.data = data
        this.cb = cb
        if (ctx != null) {
            eFont = FontDemo(ctx)
        }
    }
}