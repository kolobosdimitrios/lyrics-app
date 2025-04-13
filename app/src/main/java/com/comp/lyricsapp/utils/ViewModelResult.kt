package com.comp.lyricsapp.utils


sealed class ViewModelResult<out T> {

    data class Success<out T>(val data: T) : ViewModelResult<T>()
    data class Error<out T>(val exception: Exception, val data: T) : ViewModelResult<Nothing>()
    data class Loading<out T>(val data: T) : ViewModelResult<T>()
}