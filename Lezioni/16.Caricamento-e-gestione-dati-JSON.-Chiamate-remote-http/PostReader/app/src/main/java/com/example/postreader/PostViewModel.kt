package com.example.postreader

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class PostViewModel: ViewModel() {
    private val _posts = mutableStateOf<List<Post>>(emptyList())
    val posts: State<List<Post>> = _posts

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _errorMessage= mutableStateOf("")
    val errorMessage: State<String> = _errorMessage

    init {
        fetchPosts()
    }

    private fun fetchPosts() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = RetrofitInstance.api.getPosts()
                _posts.value = response
                _errorMessage.value = ""
            } catch (e: Exception) {
                _errorMessage.value = "Errore di connessione : ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}
