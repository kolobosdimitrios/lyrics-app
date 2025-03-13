package com.comp.lyricsapp.presentation.screens.project

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.comp.lyricsapp.domain.entities.Bar
import com.comp.lyricsapp.domain.entities.Line
import com.comp.lyricsapp.presentation.theme.Typography
import com.comp.lyricsapp.presentation.view_models.BarViewModel
import com.comp.lyricsapp.presentation.view_models.LineViewModel
import com.comp.lyricsapp.presentation.view_models.ProjectViewModel

@Composable
fun ProjectScreen(
    navController: NavController,
    projectId: Long?,
    projectViewModel: ProjectViewModel = hiltViewModel(),
    barViewModel: BarViewModel = hiltViewModel(),
    lineViewModel: LineViewModel = hiltViewModel()
    ){
    var projectTitle by remember { mutableStateOf("") }
    var savedProjectTitle by remember { mutableStateOf("") }

    val focusManager = LocalFocusManager.current  // Manages focus
    val keyboardController = LocalSoftwareKeyboardController.current  // Manages keyboard
    val focusRequester = remember { FocusRequester() } // Focus controller

    val savedProjectWithBars by projectViewModel.selectedProjectWithBars.collectAsState()
    var projectLines by remember { mutableStateOf<List<Line>>(emptyList()) } // ✅ MutableState for recomposition


    LaunchedEffect(projectId) {
        projectId?.let { id ->
            projectViewModel.getProjectWithBars(id)
        }
    }

    // ✅ Keeps the UI updated when the project state changes
    LaunchedEffect(savedProjectWithBars) {
        savedProjectWithBars?.let { projectWithBars ->
            projectTitle = projectWithBars.project.title
            val bars = projectWithBars.bars
            for(bar: Bar in bars){

            }
        }
    }


    Scaffold (
        topBar = {
            TopAppBar(
                title = {

                    TextField(
                        modifier = Modifier.fillMaxWidth().focusRequester(focusRequester),
                        value = projectTitle,
                        onValueChange = { newValue -> projectTitle = newValue},
                        placeholder = { Text("Project Title", style = Typography.h1) },
                        singleLine = true,
                        textStyle = Typography.h1,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Done  // Set Done button on keyboard
                        ),
                        trailingIcon = {

                            Row(){
                                IconButton(
                                    onClick = {
                                        focusRequester.requestFocus()  // Move focus to TextField
                                        keyboardController?.show()
                                    }
                                ) {
                                    Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit Title")
                                }

                                IconButton(
                                    onClick = {
                                        savedProjectWithBars?.project?.let {
                                            it.title = savedProjectTitle
                                            projectViewModel.updateProject(it)
                                        }
                                        keyboardController?.hide()
                                        focusManager.clearFocus()
                                    }
                                ) {
                                    Icon(imageVector = Icons.Default.Save, contentDescription = "Save Title")
                                }
                            }

                        },
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color.Transparent,
                            cursorColor = Color.White,
                            focusedIndicatorColor = Color.Transparent,  // 👈 Removes line when focused
                            unfocusedIndicatorColor = Color.Transparent  // 👈 Removes line when unfocused

                        ),
                        keyboardActions = KeyboardActions(onDone = {
                            savedProjectTitle = projectTitle  // Save text
                            focusManager.clearFocus()  // Remove focus from TextField
                            keyboardController?.hide()  // Hide keyboard
                        })
                    )

                }
            )
        }
    ){ innerPadding ->
        Box(
            modifier = Modifier.padding(innerPadding)
        ) {

        }
    }
}


