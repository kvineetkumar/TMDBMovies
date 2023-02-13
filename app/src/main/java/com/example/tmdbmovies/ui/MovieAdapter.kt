package com.example.tmdbmovies.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.tmdbmovies.api.ApiClient
import com.example.tmdbmovies.databinding.ItemMovieRecyclerviewBinding
import com.example.tmdbmovies.model.Movie
import com.squareup.picasso.Picasso

class MovieAdapter() :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private var movies: MutableList<Movie> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            ItemMovieRecyclerviewBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = movies.size

    fun updateAll(movies: List<Movie>) {
        val prevLastIndex = this.movies.size
        val isFirstTime = this.movies.size == 0
        this.movies.addAll(movies)
        this.movies = this.movies.toSet().toMutableList()
        notifyItemRangeChanged(if (isFirstTime) 0 else prevLastIndex, this.movies.size)
    }

    class MovieViewHolder(private val binding: ItemMovieRecyclerviewBinding) :
        ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            Picasso.get().load(ApiClient.IMAGE_URL.plus(movie.posterPath))
                .into(binding.posterImageview)
        }
    }
}