package com.app.practical.repository

import com.app.practical.api.BaseApiResponse
import com.app.practical.api.RemoteDataSource
import com.app.practical.model.Event
import com.app.practical.model.Login
import com.app.practical.utils.NetworkResult
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@ActivityRetainedScoped
class RepositoryHome @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : BaseApiResponse() {

    suspend fun requestLogin(
        email: String?, password: String?, userDeviceId: String?, fcmToken: String?
    ): Flow<NetworkResult<Login>> {
        return flow {
            emit(safeApiCall {
                remoteDataSource.requestLogin(
                    email, password, userDeviceId, fcmToken
                )
            })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun fetchEvent(
        userId: String?, userDeviceId: String?, accessToken: String?
    ): Flow<NetworkResult<Event>> {
        return flow {
            emit(safeApiCall {
                remoteDataSource.fetchEvent(
                    userId, userDeviceId, accessToken
                )
            })
        }.flowOn(Dispatchers.IO)
    }
}