package com.comp.lyricsapp.presentation.ui.project.list

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.comp.lyricsapp.domain.entities.Project
import com.comp.lyricsapp.presentation.view_models.ProjectViewModel
import com.comp.lyricsapp.presentation.ui.theme.MyAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProjectListActivity: ComponentActivity() {

    private val projectViewModel: ProjectViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContent {
            ProjectsListScreen()
        }
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    @Composable
    fun ProjectsListScreen(){

        val projectEntities by projectViewModel.projects.collectAsState()

        MyAppTheme {

            Box(Modifier.fillMaxSize()){
                ProjectsList(projectEntities)
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

    }

    @Composable
    fun ProjectsList(projectEntities: List<Project>){
        LazyColumn(
        ) {
            items(projectEntities){ project ->
                ProjectViewHolder(project)
            }
        }

    }

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

}