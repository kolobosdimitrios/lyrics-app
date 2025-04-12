package com.comp.lyricsapp.presentation.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.comp.lyricsapp.domain.entities.Project
import com.comp.lyricsapp.presentation.navigation.Screen

@Composable
fun QuickActionBoxGridVertical(navController: NavController, projects: List<Project>){

    LazyVerticalStaggeredGrid(

        columns = StaggeredGridCells.Fixed(2),
        modifier = Modifier.wrapContentSize().padding(15.dp),
        verticalItemSpacing = 8.dp,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {

        items(projects) {project ->
             //Render info boxes with data
            ProjectBox(
                project = project,
                onProjectClickListener = { selectedProject ->
                    navController.navigate(Screen.Project.route +"/${selectedProject.id}") // ✅ Unique click action
                }
            )
        }
    }

}