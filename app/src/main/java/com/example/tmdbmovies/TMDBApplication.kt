package com.example.tmdbmovies

import android.app.Application

class TMDBApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: TMDBApplication
            private set
    }
}