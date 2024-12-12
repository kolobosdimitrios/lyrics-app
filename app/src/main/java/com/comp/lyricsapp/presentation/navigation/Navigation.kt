package com.comp.lyricsapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.comp.lyricsapp.presentation.view.home_screen.HomeScreen
import com.comp.lyricsapp.presentation.view.project_list.ProjectsScreen
import com.comp.lyricsapp.presentation.view_models.ProjectViewModel


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

        composable(route = Screen.ProjectsList.route){
            val projectViewModel : ProjectViewModel = hiltViewModel()
            ProjectsScreen(projectViewModel = projectViewModel)
        }

    }

}