package com.app.just_code_now.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.just_code_now.model.Comment
import com.app.just_code_now.model.Photo
import com.app.just_code_now.model.Post
import com.app.just_code_now.repository.RepositoryHome
import com.app.just_code_now.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VmHome @Inject constructor(
    private val repository: RepositoryHome, application: Application
) : AndroidViewModel(application) {

    private val _responsePost: MutableLiveData<NetworkResult<MutableList<Post>>> = MutableLiveData()
    private val _responseComment: MutableLiveData<NetworkResult<MutableList<Comment>>> = MutableLiveData()
    private val _responsePhoto: MutableLiveData<NetworkResult<MutableList<Photo>>> = MutableLiveData()

    val responsePost: LiveData<NetworkResult<MutableList<Post>>> = _responsePost
    val responseComment: LiveData<NetworkResult<MutableList<Comment>>> = _responseComment
    val responsePhoto: LiveData<NetworkResult<MutableList<Photo>>> = _responsePhoto

    ///////////////////////////////////////////////////////////////////////////
    // Post List
    ///////////////////////////////////////////////////////////////////////////

    fun fetchPost() = viewModelScope.launch {
        repository.fetchPost().collect { values: NetworkResult<MutableList<Post>> ->
            run {
                _responsePost.value = values
            }
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // Comment List
    ///////////////////////////////////////////////////////////////////////////

    fun fetchComment(postId: String?) = viewModelScope.launch {
        repository.fetchComment(postId).collect { values: NetworkResult<MutableList<Comment>> ->
            run {
                _responseComment.value = values
            }
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // Photo List
    ///////////////////////////////////////////////////////////////////////////

    fun fetchPhoto() = viewModelScope.launch {
        repository.fetchPhoto().collect { values: NetworkResult<MutableList<Photo>> ->
            run {
                _responsePhoto.value = values
            }
        }
    }
}