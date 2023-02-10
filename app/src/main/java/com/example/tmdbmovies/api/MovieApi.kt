package com.example.tmdbmovies.api

import com.example.tmdbmovies.model.MovieListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("popular")
    suspend fun getPopularMovies(
        @Query("page") page: Int = 1,
        @Query("api_key") apiKey: String = ApiClient.API_KEY,
        @Query("language") language: String = "en_US"
    ): MovieListResponse?
}