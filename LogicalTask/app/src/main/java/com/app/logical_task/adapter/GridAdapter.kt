package com.app.logical_task.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.logical_task.R
import com.app.logical_task.databinding.ItemGridBinding
import com.app.logical_task.interfaces.GridInterface
import com.app.logical_task.model.GridData

class GridAdapter(
    private var row: Int, private var colum: Int, private var gridList: MutableList<GridData>,
    private var gridInterface: GridInterface
) : RecyclerView.Adapter<GridAdapter.GridBinding>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridBinding {
        return GridBinding(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.item_grid, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: GridBinding, position: Int) {
        val model: GridData = gridList[position]

        holder.binding.txtIndex.text = position.toString()

        if (model.isSelected == true) {
            holder.binding.root.setBackgroundColor(R.color.white)
            holder.binding.txtIndex.visibility = View.GONE
        } else {
            holder.binding.root.setBackgroundColor(R.color.navy)
        }

        holder.binding.root.setOnClickListener {

            // Calculate the row and column of the clicked item
            val clickedRow = position / colum
            val clickedColumn = position % colum

            // Calculate the indices of adjacent items
            val leftIndex = if (clickedColumn > 0) position - 1 else -1
            val rightIndex = if (clickedColumn < colum - 1) position + 1 else -1
            val topIndex = if (clickedRow > 0) position - colum else -1
            val bottomIndex = if (clickedRow < row - 1) position + colum else -1

            gridInterface.onClicked(
                position, leftIndex, rightIndex, topIndex, bottomIndex, gridList.size
            )
        }
    }


    override fun getItemCount(): Int {
        return gridList.size
    }

    inner class GridBinding(var binding: ItemGridBinding) : RecyclerView.ViewHolder(binding.root)
}