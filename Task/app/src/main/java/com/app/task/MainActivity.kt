package com.app.task

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.app.task.utils.Utils
import com.app.task.adapter.OrderAdapter
import com.app.task.databinding.ActivityMainBinding
import com.app.task.db.AppDatabase
import com.app.task.db.AppDatabase.Companion.getAppDataBase
import com.app.task.db.entity.DbOrder
import com.app.task.interfaces.OrderInterface
import com.app.task.model.OrderKotlin
import com.app.task.utils.NetworkResult
import com.app.task.viewModel.VmOrder
import com.google.gson.JsonObject
import dagger.hilt.android.AndroidEntryPoint
import java.io.IOException

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var b: ActivityMainBinding
    private val vmOrder by viewModels<VmOrder>()
    private var order: OrderKotlin? = null
    private var orderAdapter: OrderAdapter? = null
    private var db: AppDatabase? = null

    ///////////////////////////////////////////////////////////////////////////
    // Override Methods
    ///////////////////////////////////////////////////////////////////////////

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = DataBindingUtil.setContentView(this@MainActivity, R.layout.activity_main)
        init()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (vmOrder.responseOrder.hasObservers()) vmOrder.responseOrder.removeObservers(this)
    }

    ///////////////////////////////////////////////////////////////////////////
    // Custom Methods
    ///////////////////////////////////////////////////////////////////////////

    private fun init() {
        db = getAppDataBase(this@MainActivity)

        b.btnDelete.setOnClickListener {
            Toast.makeText(
                this@MainActivity, "Database deleted.", Toast.LENGTH_LONG
            ).show()
            db!!.orderDao().clearData()
        }

        if (Utils.isOnline(this@MainActivity)) {
            fetchEvent()
        } else {
            Toast.makeText(this@MainActivity, "No Internet.", Toast.LENGTH_SHORT).show()
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // Requests
    ///////////////////////////////////////////////////////////////////////////

    private fun fetchEvent() {
        b.rvOrder.adapter = null

        val jsonObject = JsonObject()
        jsonObject.addProperty("restaurant_id", "1")
        jsonObject.addProperty("status", 4)
        jsonObject.addProperty("page", 1)
        jsonObject.addProperty("search", "abc")

        vmOrder.fetchEvent(jsonObject)
        if (!vmOrder.responseOrder.hasObservers()) {
            vmOrder.responseOrder.observe(this@MainActivity, observerEvent)
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // Observers
    ///////////////////////////////////////////////////////////////////////////

    private val observerEvent: androidx.lifecycle.Observer<NetworkResult<OrderKotlin>> =
        androidx.lifecycle.Observer { response ->
            when (response) {
                is NetworkResult.Success -> {
                    response.data?.let {
                        try {
                            order = it
                            if (order!!.data!!.orderInfo!!.orders.size > 0) {
                                Toast.makeText(
                                    this@MainActivity, "SUCCESS.", Toast.LENGTH_LONG
                                ).show()
                                orderAdapter = OrderAdapter(order, orderInterface)
                                b.rvOrder.adapter = orderAdapter
                            } else {
                                vmOrder.responseOrder.removeObservers(this)
                                Toast.makeText(
                                    this@MainActivity, "FAIL.", Toast.LENGTH_LONG
                                ).show()
                            }
                        } catch (e: IOException) {
                            vmOrder.responseOrder.removeObservers(this)
                            Toast.makeText(
                                this@MainActivity, "FAIL.", Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }

                is NetworkResult.Error -> {
                    vmOrder.responseOrder.removeObservers(this)
                    Toast.makeText(
                        this@MainActivity, "FAIL.", Toast.LENGTH_LONG
                    ).show()
                }

                is NetworkResult.Loading -> {}
            }
        }

    ///////////////////////////////////////////////////////////////////////////
    // Callbacks
    ///////////////////////////////////////////////////////////////////////////

    private val orderInterface: OrderInterface = OrderInterface { data, _ ->
        Toast.makeText(
            this@MainActivity, "Data Added in Local Database.", Toast.LENGTH_LONG
        ).show()
        db!!.orderDao().insertOne(DbOrder(data.orderId, data.orderId.toString()))
    }
}