package com.app.logical_task

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.app.logical_task.activity.SecondActivity
import com.app.logical_task.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var b: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = DataBindingUtil.setContentView(this@MainActivity, R.layout.activity_main)
        init()
    }

    private fun init() {
        b.btnSubmit.setOnClickListener {
            startActivity(
                Intent(
                    this@MainActivity, SecondActivity::class.java
                ).putExtra(
                    "Row", b.edtRow.text.toString().trim()
                ).putExtra(
                    "Colum", b.edtColum.text.toString().trim()
                )
            )
        }
    }
}