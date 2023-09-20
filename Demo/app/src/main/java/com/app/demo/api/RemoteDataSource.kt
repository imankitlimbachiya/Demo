package com.app.demo.api

import com.app.demo.interfaces.ApiInterface
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val service: ApiInterface) {

    suspend fun fetchAvailableList() = service.fetchAvailableList()
}