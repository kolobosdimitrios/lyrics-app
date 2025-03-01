package com.comp.lyricsapp.presentation.screens.home

import androidx.annotation.ColorRes

data class QuickAction(
    val title: String,
    val body : String?,
    @ColorRes val backgroundColorStart: Int, //Resource ids for the background colors
    @ColorRes val backgroundColorEnd: Int,
    /*on Click action*/val onClick: () -> Unit
)
