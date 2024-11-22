package com.comp.lyricsapp.ui.home

import android.os.Bundle
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
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.comp.lyricsapp.data.model.ProjectEntity
import com.comp.lyricsapp.ui.theme.MyAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {


    private val homeViewModel: HomeViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            HomeScreen(

            )
        }


    }

    private fun createProject(projectEntity: ProjectEntity) {

        homeViewModel.createProject(projectEntity)
    }

    @Composable
    fun HomeScreen(){

        val projectEntities by homeViewModel.projects.collectAsState()

        MyAppTheme {

            Box(Modifier.fillMaxSize()){
                ProjectsList(projectEntities)
                Button(
                    onClick = {
                        createProject(
                            ProjectEntity(id = null, title = "Project")
                        )
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

}

@Composable
fun ProjectsList(projectEntities: List<ProjectEntity>){
    LazyColumn(
    ) {
        items(projectEntities){ project ->
            ProjectViewHolder(project)
        }
    }

}

@Composable
fun ProjectViewHolder(projectEntity: ProjectEntity){
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
                "${projectEntity.title} No. ${projectEntity.id}",
                Toast.LENGTH_SHORT
            ).show()
                                          },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ){
        Text(
            text = projectEntity.title + " No.${projectEntity.id}",
            Modifier
                .padding(16.dp)

        )
    }
}





