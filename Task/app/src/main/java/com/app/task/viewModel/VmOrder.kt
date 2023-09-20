package com.app.task.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.task.utils.NetworkResult
import com.app.task.model.OrderKotlin
import com.app.task.repository.RepositoryOrder
import com.google.gson.JsonObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VmOrder @Inject constructor(
    private val repository: RepositoryOrder, application: Application
) : AndroidViewModel(application) {

    private val _responseOrder: MutableLiveData<NetworkResult<OrderKotlin>> = MutableLiveData()

    val responseOrder: LiveData<NetworkResult<OrderKotlin>> = _responseOrder

    ///////////////////////////////////////////////////////////////////////////
    // Order List
    ///////////////////////////////////////////////////////////////////////////

    fun fetchEvent(
        body: JsonObject
    ) = viewModelScope.launch {
        repository.fetchEvent(
            body
        ).collect { values: NetworkResult<OrderKotlin> ->
            run { _responseOrder.value = values }
        }
    }
}