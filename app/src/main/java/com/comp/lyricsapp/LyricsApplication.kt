package com.comp.lyricsapp

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class LyricsApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        Log.d("LyricsApplication", "Application started")
    }
}