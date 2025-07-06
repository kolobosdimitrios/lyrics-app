package com.comp.lyricsapp.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.getString
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.comp.lyricsapp.R
import com.comp.lyricsapp.domain.entities.Project
import com.comp.lyricsapp.presentation.theme.Shapes
import com.comp.lyricsapp.presentation.theme.Typography
import com.comp.lyricsapp.presentation.view_models.ProjectViewModel
import com.comp.lyricsapp.utils.formatTimestamp
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    navController: NavController,
    projectViewModel: ProjectViewModel = hiltViewModel()
) {

    val scaffoldState = rememberBottomSheetScaffoldState()
    val scope = rememberCoroutineScope()

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetPeekHeight = 0.dp,
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){

                        Text(
                            style = Typography.h1,
                            text = getString(LocalContext.current, R.string.home_screen)
                        )
                        IconButton(
                            onClick = {
                                scope.launch {
                                    scaffoldState.bottomSheetState.expand()
                                }
                            }
                        ) {
                            Icon(imageVector = Icons.Default.Add, contentDescription = "Add new Project")
                        }
                    }
                }
            )
        },
        sheetShape = Shapes.medium,
        sheetContent = {
            CreateProjectSheet(
                onSaveClick = { projectTitle ->
                    projectViewModel.createProject(
                        Project(
                            title = projectTitle,
                            timeStamp = formatTimestamp(System.currentTimeMillis())
                        )
                    )
                    scope.launch {
                        scaffoldState.bottomSheetState.collapse()
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            ProjectsGrid(
                navController = navController
            )
        }
    }

}

@Composable
fun CreateProjectSheet(
    onSaveClick: (String) -> Unit,
){

    var projectTitle by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.run { padding(16.dp) }
    ) {
        Text(getString(LocalContext.current, R.string.create_project), style = Typography.h1)

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = projectTitle,
            onValueChange = { projectTitle = it },
            label = { Text(getString(LocalContext.current, R.string.project_title_placeholder)) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val projectTitleTrimmed = projectTitle.trim()
                projectTitleTrimmed.takeIf { it.isNotBlank() }?.let{
                    onSaveClick(it)
                    projectTitle = ""
                }
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(getString(LocalContext.current, R.string.create), style = Typography.h1)
        }
    }
}




