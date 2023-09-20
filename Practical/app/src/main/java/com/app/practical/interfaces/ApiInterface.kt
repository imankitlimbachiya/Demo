package com.app.practical.interfaces

import com.app.practical.model.Event
import com.app.practical.model.Login
import com.app.practical.utils.Configs.*
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiInterface {

    @FormUrlEncoded
    @POST(API_LOGIN)
    suspend fun requestLogin(
        @Field("email") email: String?,
        @Field("password") password: String?,
        @Field("user_device_id") userDeviceId: String?,
        @Field("fcm_token") fcmToken: String?
    ): Response<Login>

    @FormUrlEncoded
    @POST(API_EVENT_LIST)
    suspend fun fetchEvent(
        @Field("user_id") userId: String?,
        @Field("user_device_id") userDeviceId: String?,
        @Field("access_token") accessToken: String?
    ): Response<Event>
}