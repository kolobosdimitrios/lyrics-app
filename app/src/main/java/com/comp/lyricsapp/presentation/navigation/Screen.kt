package com.comp.lyricsapp.presentation.navigation

sealed class Screen(val route: String) {

    object Home: Screen("Home")

}