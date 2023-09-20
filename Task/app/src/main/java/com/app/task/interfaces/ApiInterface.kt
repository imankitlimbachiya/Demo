package com.app.task.interfaces

import com.app.task.model.OrderKotlin
import com.app.task.utils.Configs
import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiInterface {

    @Headers("Content-Type: application/json")
    @POST(Configs.API_ORDER)
    suspend fun fetchOrder(
        @Body body: JsonObject
    ): Response<OrderKotlin>
}