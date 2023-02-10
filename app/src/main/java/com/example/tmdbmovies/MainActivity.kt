package com.example.tmdbmovies

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.tmdbmovies.api.ApiClient
import com.example.tmdbmovies.api.MovieApi
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var movieApi: MovieApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        movieApi = ApiClient.getClient()

        //sample code to test the movie api.
        lifecycleScope.launch {
            val movieListResponse = movieApi.getPopularMovies(page = 1)
            val movies = movieListResponse?.results
            Log.d(localClassName, movies.toString())
        }
    }
}