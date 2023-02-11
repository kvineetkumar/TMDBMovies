package com.example.tmdbmovies.api

import com.example.tmdbmovies.TMDBApplication
import com.example.tmdbmovies.utility.NetworkUtil
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    companion object {
        private val BASE_URL = "https://api.themoviedb.org/3/movie/"

        val IMAGE_URL = "https://image.tmdb.org/t/p/w500/"
        val API_KEY = "3c7aa4e2134f3d58ed766df82c4353d0"

        fun getClient(): MovieApi {
            val context = TMDBApplication.instance.applicationContext

            val cacheSize = (5 * 1024 * 1024).toLong()
            val cacheDir = Cache(context.cacheDir, cacheSize)

            val okhttpClient = OkHttpClient.Builder()
                .cache(cacheDir)
                .addInterceptor { chain ->
                    var request = chain.request()
                    request = if (NetworkUtil.isNetworkConnected(context))
                        request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build()
                    else
                        request.newBuilder().header(
                            "Cache-Control",
                            "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7
                        ).build()
                    chain.proceed(request)
                }
                .build()

            val retrofit = Retrofit.Builder().baseUrl(BASE_URL).client(okhttpClient)
                .addConverterFactory(GsonConverterFactory.create()).build()

            val movieApi = retrofit.create(MovieApi::class.java)
            return movieApi
        }
    }
}