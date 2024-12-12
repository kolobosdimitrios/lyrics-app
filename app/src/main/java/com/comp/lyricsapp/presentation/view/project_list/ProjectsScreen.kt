package com.comp.lyricsapp.presentation.view.project_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.comp.lyricsapp.presentation.view_models.ProjectViewModel

@Composable
fun ProjectsScreen(projectViewModel: ProjectViewModel){

    Box(Modifier.fillMaxSize()){
        val projectEntities by projectViewModel.projects.collectAsState()

        Button(
            onClick = {
                //TODO:Navigate to the project creation activity
            },
            Modifier
                .align(Alignment.BottomEnd)
                .padding(32.dp)

        ) {
            Icon(Icons.Rounded.Add, "Add new Lyrics Project")
        }
    }
}
