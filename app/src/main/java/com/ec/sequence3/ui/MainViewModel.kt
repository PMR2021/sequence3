package com.ec.sequence3.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ec.sequence3.data.PostRepository
import com.ec.sequence3.data.model.Post
import kotlinx.coroutines.launch
import java.lang.Error

// ViewModel
class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val postRepository by lazy { PostRepository.newInstance(application) }

    val posts = MutableLiveData<ViewState>()

    fun loadPost() {

        viewModelScope.launch {
            posts.value = ViewState.Loading
            try {
                posts.value = ViewState.Content(posts = postRepository.getPost())

            } catch (e: Exception) {
                posts.value = ViewState.Error(e.message.orEmpty())
            }

        }
    }

    sealed class ViewState {
        object Loading : ViewState()
        data class Content(val posts: List<Post>) : ViewState()
        data class Error(val message: String) : ViewState()
    }
}