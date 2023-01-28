package com.example.jetpackcompose.ui.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackcompose.data.model.movies.MoviesResponse
import com.example.jetpackcompose.data.repository.ApiRepository
import com.example.jetpackcompose.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ApiViewModel : ViewModel(){
    var movies : List<MoviesResponse> by mutableStateOf(listOf())
    var progressBarStatus = MutableLiveData<Resource<List<MoviesResponse>>>()

    lateinit var clickedItem: MoviesResponse

    init {
        getAllEmployees()
    }

    private fun getAllEmployees() {
        viewModelScope.launch {
            progressBarStatus.value = Resource.loading(null)
            try {
                val response = ApiRepository.getAllMovies()

                withContext(Dispatchers.Main) {
                    response.let {
                        if (response.isSuccessful) {
                            progressBarStatus.value = Resource.success(it.body()!!.moviesResponses)
                            movies = it.body()!!.moviesResponses
                        } else {
                            progressBarStatus.value = Resource.error("Unknown Error", null)
                        }
                    }
                }
            } catch (ex : Exception) {
                ex.message
            }
        }
    }

    fun itemClicked(item: MoviesResponse) {
        clickedItem = item
    }
}