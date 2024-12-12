package com.comp.lyricsapp.presentation.view.project_list

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.comp.lyricsapp.domain.entities.Project

@Composable
fun ProjectsList(projectEntities: List<Project>){
    LazyColumn(
    ) {
        items(projectEntities){ project ->
            ProjectViewHolder(project)
        }
    }

}