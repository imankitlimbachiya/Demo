package com.app.just_code_now.interfaces

import com.app.just_code_now.model.Comment
import com.app.just_code_now.model.Photo
import com.app.just_code_now.model.Post
import com.app.just_code_now.utils.Configs
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET(Configs.API_POSTS)
    suspend fun fetchPost(): Response<MutableList<Post>>

    @GET(Configs.API_COMMENTS)
    suspend fun fetchComment(
        @Query("postId") postId: String?
    ): Response<MutableList<Comment>>

    @GET(Configs.API_PHOTOS)
    suspend fun fetchPhoto(): Response<MutableList<Photo>>
}