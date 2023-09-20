package com.app.just_code_now.api

import com.app.just_code_now.interfaces.ApiInterface
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val service: ApiInterface) {

    suspend fun fetchPost() = service.fetchPost()

    suspend fun fetchComment(postId: String?) = service.fetchComment(postId)

    suspend fun fetchPhoto() = service.fetchPhoto()
}