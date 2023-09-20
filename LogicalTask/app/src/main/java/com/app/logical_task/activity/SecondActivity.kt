package com.app.logical_task.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.app.logical_task.R
import com.app.logical_task.adapter.GridAdapter
import com.app.logical_task.databinding.ActivitySecondBinding
import com.app.logical_task.interfaces.GridInterface
import com.app.logical_task.model.GridData

class SecondActivity : AppCompatActivity() {

    private lateinit var b: ActivitySecondBinding
    private var adapter: GridAdapter? = null
    private var layoutManager: GridLayoutManager? = null
    private val gridList: MutableList<GridData> = ArrayList()
    private var row: String? = null
    private var colum: String? = null
    private var count: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = DataBindingUtil.setContentView(this@SecondActivity, R.layout.activity_second)
        init()
    }

    private fun init() {
        row = intent.getStringExtra("Row")
        colum = intent.getStringExtra("Colum")

        count = row!!.toInt() * colum!!.toInt()

        for (i in 0 until count!!) {
            gridList.add(i, GridData(false))
        }

        initAdapter()

        b.btnNext.setOnClickListener {
            startActivity(Intent(this@SecondActivity, ThirdActivity::class.java))
        }
    }

    private fun initAdapter() {
        b.root.visibility = View.GONE
        if (adapter != null) b.rvGrid.adapter = null
        layoutManager = GridLayoutManager(this@SecondActivity, colum!!.toInt())
        b.rvGrid.layoutManager = layoutManager
        adapter = GridAdapter(row!!.toInt(), colum!!.toInt(), gridList, gridInterface)
        b.rvGrid.adapter = adapter
        b.root.visibility = View.VISIBLE
    }

    private val gridInterface: GridInterface = object : GridInterface {
        override fun onClicked(pos: Int, left: Int, right: Int, top: Int, bottom: Int, size: Int) {
            if (gridList.isNotEmpty()) gridList.clear()
            for (i in 0 until size) {
                when (i) {
                    pos -> {
                        gridList.add(i, GridData(true))
                    }

                    left -> {
                        gridList.add(i, GridData(true))
                    }

                    right -> {
                        gridList.add(i, GridData(true))
                    }

                    top -> {
                        gridList.add(i, GridData(true))
                    }

                    bottom -> {
                        gridList.add(i, GridData(true))
                    }

                    else -> {
                        gridList.add(i, GridData(false))
                    }
                }
            }
            initAdapter()
        }
    }
}
