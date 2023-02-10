package com.example.tmdbmovies.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.tmdbmovies.MovieViewModel
import com.example.tmdbmovies.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel = ViewModelProvider(this).get(MovieViewModel::class.java)

        val adapter = MovieAdapter()
        binding.movieRecyclerView.apply {
            this.adapter = adapter
        }

        viewModel.moviesLiveData.observe(this) {
            adapter.updateAll(it)
        }

        viewModel.getMovies()
    }
}