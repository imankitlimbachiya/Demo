package com.app.logical_task.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.app.logical_task.R
import com.app.logical_task.databinding.ActivityThirdBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ThirdActivity : AppCompatActivity() {

    private lateinit var b: ActivityThirdBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = DataBindingUtil.setContentView(this@ThirdActivity, R.layout.activity_third)
        init()
    }

    private fun init() {

    }
}