package com.app.task.repository

import com.app.task.api.BaseApiResponse
import com.app.task.utils.NetworkResult
import com.app.task.api.RemoteDataSource
import com.app.task.model.OrderKotlin
import com.google.gson.JsonObject
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@ActivityRetainedScoped
class RepositoryOrder @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : BaseApiResponse() {

    suspend fun fetchEvent(
        body: JsonObject
    ): Flow<NetworkResult<OrderKotlin>> {
        return flow {
            emit(safeApiCall {
                remoteDataSource.fetchEvent(
                    body
                )
            })
        }.flowOn(Dispatchers.IO)
    }
}