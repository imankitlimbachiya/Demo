package com.app.practical.api

import com.app.practical.interfaces.ApiInterface
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val service: ApiInterface) {

    suspend fun requestLogin(
        email: String?, password: String?, userDeviceId: String?, fcmToken: String?
    ) = service.requestLogin(
        email, password, userDeviceId, fcmToken
    )

    suspend fun fetchEvent(
        userId: String?, userDeviceId: String?, accessToken: String?
    ) = service.fetchEvent(
        userId, userDeviceId, accessToken
    )
}