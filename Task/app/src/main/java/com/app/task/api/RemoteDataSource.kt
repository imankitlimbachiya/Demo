package com.app.task.api

import com.app.task.interfaces.ApiInterface
import com.google.gson.JsonObject
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val service: ApiInterface) {

    suspend fun fetchEvent(
        body: JsonObject
    ) = service.fetchOrder(
        body
    )
}