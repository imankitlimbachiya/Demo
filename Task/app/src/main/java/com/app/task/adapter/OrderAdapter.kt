package com.app.task.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.task.R
import com.app.task.databinding.ItemOrderBinding
import com.app.task.interfaces.OrderInterface
import com.app.task.model.OrderKotlin
import com.app.task.model.Orders

class OrderAdapter(
    private var order: OrderKotlin?, private var orderInterface: OrderInterface?
) : RecyclerView.Adapter<OrderAdapter.VhOrder?>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VhOrder {
        return VhOrder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.item_order, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: VhOrder, position: Int) {
        val model: Orders = order!!.data!!.orderInfo!!.orders[position]
        holder.b.txtOrderId.text = model.orderId.toString()
        holder.b.txtSequenceNo.text = model.sequenceNo.toString()
        holder.b.txtOrderType.text = model.orderType.toString()
        holder.b.txtExpectedDate.text = model.expectedDate.toString()

        holder.b.root.setOnClickListener {
            if (orderInterface != null) {
                orderInterface!!.onClicked(model, position)
            }
        }
    }

    override fun getItemCount(): Int {
        return order!!.data!!.orderInfo!!.orders.size
    }

    class VhOrder(b: ItemOrderBinding) : RecyclerView.ViewHolder(b.root) {

        val b: ItemOrderBinding

        init {
            this.b = b
        }
    }
}