package com.comp.lyricsapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.comp.lyricsapp.presentation.screens.home.HomeScreen
import com.comp.lyricsapp.presentation.screens.project.ProjectScreen


@Composable
fun Navigation(){

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Home.route){
        /*
         *  Add all composable endpoints of the apps aka Screens.
         */
        composable(route = Screen.Home.route){
            HomeScreen(navController)
        }

        composable(route = Screen.Project.route){
            ProjectScreen(navController)
        }

    }

}