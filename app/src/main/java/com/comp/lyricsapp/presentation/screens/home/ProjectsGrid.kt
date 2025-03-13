package com.comp.lyricsapp.presentation.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.comp.lyricsapp.presentation.view_models.ProjectViewModel

@Composable
fun ProjectsGrid(
    navController: NavController,
    projectViewModel: ProjectViewModel = hiltViewModel()
){
    val projectsList by projectViewModel.projectsList.collectAsState()
    QuickActionBoxGridVertical(projects = projectsList, navController = navController)
}