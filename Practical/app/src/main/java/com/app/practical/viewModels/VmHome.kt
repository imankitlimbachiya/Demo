package com.app.practical.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.practical.repository.RepositoryHome
import com.app.practical.model.Event
import com.app.practical.model.Login
import com.app.practical.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VmHome @Inject constructor(
    private val repository: RepositoryHome, application: Application
) : AndroidViewModel(application) {

    private val _responseLogin: MutableLiveData<NetworkResult<Login>> = MutableLiveData()
    private val _responseEvent: MutableLiveData<NetworkResult<Event>> = MutableLiveData()

    val responseLogin: LiveData<NetworkResult<Login>> = _responseLogin
    val responseEvent: LiveData<NetworkResult<Event>> = _responseEvent

    ///////////////////////////////////////////////////////////////////////////
    // Company List
    ///////////////////////////////////////////////////////////////////////////

    fun requestLogin(
        email: String?, password: String?, userDeviceId: String?, fcmToken: String?
    ) = viewModelScope.launch {
        repository.requestLogin(
            email, password, userDeviceId, fcmToken
        ).collect { values: NetworkResult<Login> ->
            run { _responseLogin.value = values }
        }
    }

    fun fetchEvent(
        userId: String?, userDeviceId: String?, accessToken: String?
    ) = viewModelScope.launch {
        repository.fetchEvent(
            userId, userDeviceId, accessToken
        ).collect { values: NetworkResult<Event> ->
            run { _responseEvent.value = values }
        }
    }
}