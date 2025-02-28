package com.comp.lyricsapp.presentation.view.home_screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.comp.lyricsapp.R
import com.comp.lyricsapp.presentation.navigation.Screen

@Composable
fun DashBoard(navController: NavController){

    //QuickActionBoxGridHorizontal(quickActions= quickActions)
    QuickActionBoxGridVertical(quickActions= listOf())


}