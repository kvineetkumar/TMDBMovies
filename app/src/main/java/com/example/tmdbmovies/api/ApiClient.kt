package com.example.tmdbmovies.api

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    companion object {
        private val BASE_URL =
            "https://api.themoviedb.org/3/movie/"
        val API_KEY = "3c7aa4e2134f3d58ed766df82c4353d0"

        fun getClient(): MovieApi {
            val interceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
                override fun log(message: String) {
                    Log.d(ApiClient::class.java.name, message)
                }
            })
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

            val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val movieApi = retrofit.create(MovieApi::class.java)
            return movieApi
        }
    }
}