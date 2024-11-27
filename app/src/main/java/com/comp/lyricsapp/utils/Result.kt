package com.comp.lyricsapp.utils


sealed class Result<out T> {

    data class Success<out T>(val data: T) : Result<T>()
    data class Error<out T>(val exception: Exception, val data: T) : Result<Nothing>()
    data class Loading<out T>(val data: T) : Result<T>()
}