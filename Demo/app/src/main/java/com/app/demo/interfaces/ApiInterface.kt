package com.app.demo.interfaces

import com.app.demo.model.Available
import com.app.demo.utils.Configs.API_COIN_LIST
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET(API_COIN_LIST)
    suspend fun fetchAvailableList(): Response<Available>
}