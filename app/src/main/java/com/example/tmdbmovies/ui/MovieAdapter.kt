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

    private var movies: List<Movie> = emptyList()

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
        this.movies = movies
        notifyDataSetChanged()
    }

    class MovieViewHolder(private val binding: ItemMovieRecyclerviewBinding) :
        ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.titleTextview.text = movie.title
            Picasso.get().load(ApiClient.IMAGE_URL.plus(movie.posterPath))
                .into(binding.posterImageview)
        }
    }
}