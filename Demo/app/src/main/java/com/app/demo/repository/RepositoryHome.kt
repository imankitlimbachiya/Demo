package com.app.demo.repository

import com.app.demo.api.BaseApiResponse
import com.app.demo.api.RemoteDataSource
import com.app.demo.model.Available
import com.app.demo.utils.NetworkResult
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

    suspend fun fetchAvailableList(): Flow<NetworkResult<Available>> {
        return flow {
            emit(safeApiCall { remoteDataSource.fetchAvailableList() })
        }.flowOn(Dispatchers.IO)
    }
}