package com.app.practical.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.practical.R
import com.app.practical.databinding.ItemEventBinding
import com.app.practical.model.Event
import com.bumptech.glide.Glide

class EventAdapter(
    private var ctx: Context?, private var event: Event?
) : RecyclerView.Adapter<EventAdapter.VhEvent?>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VhEvent {
        return VhEvent(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.item_event, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: VhEvent, position: Int) {
        val model: Event.Datum = event!!.data[position]
        Glide.with(ctx!!).load(model.image).fitCenter().into(holder.b.imgEvent)
        holder.b.txtEventName.text = model.eventTitle
    }

    override fun getItemCount(): Int {
        return event!!.data.size
    }



    class VhEvent(b: ItemEventBinding) : RecyclerView.ViewHolder(b.root) {

        val b: ItemEventBinding

        init {
            this.b = b
        }
    }
}