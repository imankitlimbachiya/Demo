package com.app.demo.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.demo.model.Available
import com.app.demo.repository.RepositoryHome
import com.app.demo.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VmHome @Inject constructor(
    private val repository: RepositoryHome, application: Application
) : AndroidViewModel(application) {

    private val _responseAvailable: MutableLiveData<NetworkResult<Available>> = MutableLiveData()

    val responseAvailable: LiveData<NetworkResult<Available>> = _responseAvailable

    ///////////////////////////////////////////////////////////////////////////
    // Company List
    ///////////////////////////////////////////////////////////////////////////

    fun fetchAvailableList() = viewModelScope.launch {
        repository.fetchAvailableList().collect { values: NetworkResult<Available> ->
            run { _responseAvailable.value = values }
        }
    }
}