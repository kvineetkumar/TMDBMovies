package com.example.tmdbmovies.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.example.tmdbmovies.MovieViewModel
import com.example.tmdbmovies.PaginationScrollListener
import com.example.tmdbmovies.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel = ViewModelProvider(this).get(MovieViewModel::class.java)

        val movieRecyclerView = binding.movieRecyclerView
        val layoutManager: GridLayoutManager = movieRecyclerView.layoutManager as GridLayoutManager
        val adapter = MovieAdapter()
        movieRecyclerView.setLayoutManager(layoutManager);
        movieRecyclerView.setAdapter(adapter)
        movieRecyclerView.itemAnimator = DefaultItemAnimator()

        var page = 1
        movieRecyclerView.addOnScrollListener(object : PaginationScrollListener(layoutManager) {
            override fun loadMoreItems() {
                if (page <= viewModel.totalPages)
                    viewModel.getMovies(page++)
            }
        })

        viewModel.moviesLiveData.observe(this) {
            adapter.updateAll(it)
        }
        viewModel.getMovies()
    }
}