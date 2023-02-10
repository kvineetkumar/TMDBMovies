package com.example.tmdbmovies

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tmdbmovies.api.ApiClient
import com.example.tmdbmovies.api.MovieApi
import com.example.tmdbmovies.model.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieViewModel : ViewModel() {

    val moviesLiveData = MutableLiveData<List<Movie>>()
    private val movieApi: MovieApi = ApiClient.getClient()

    fun getMovies() {
        CoroutineScope(Dispatchers.IO).launch {
            val movieListResponse = movieApi.getPopularMovies(1)
            moviesLiveData.postValue(movieListResponse?.results)
        }
    }
}