package com.comp.lyricsapp.presentation.view.project_list

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.comp.lyricsapp.domain.entities.Project

@Composable
fun ProjectViewHolder(project: Project){
    val context = LocalContext.current
    Row (
        Modifier.fillMaxWidth()
            .shadow(
                elevation = 1.dp,
                shape = RectangleShape,
            )
            .clickable {
                Toast.makeText(
                    context,
                    "${project.title} No. ${project.id}",
                    Toast.LENGTH_SHORT
                ).show()
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ){
        Text(
            text = project.title + " No.${project.id}",
            Modifier
                .padding(16.dp)

        )
    }
}