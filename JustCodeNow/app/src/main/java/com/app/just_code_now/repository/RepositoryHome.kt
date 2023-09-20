package com.app.just_code_now.repository

import com.app.just_code_now.api.BaseApiResponse
import com.app.just_code_now.api.RemoteDataSource
import com.app.just_code_now.model.Comment
import com.app.just_code_now.model.Photo
import com.app.just_code_now.model.Post
import com.app.just_code_now.utils.NetworkResult
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

    suspend fun fetchPost(): Flow<NetworkResult<MutableList<Post>>> {
        return flow {
            emit(safeApiCall {
                remoteDataSource.fetchPost()
            })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun fetchComment(
        postId: String?
    ): Flow<NetworkResult<MutableList<Comment>>> {
        return flow {
            emit(safeApiCall {
                remoteDataSource.fetchComment(
                    postId
                )
            })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun fetchPhoto(): Flow<NetworkResult<MutableList<Photo>>> {
        return flow {
            emit(safeApiCall {
                remoteDataSource.fetchPhoto()
            })
        }.flowOn(Dispatchers.IO)
    }
}