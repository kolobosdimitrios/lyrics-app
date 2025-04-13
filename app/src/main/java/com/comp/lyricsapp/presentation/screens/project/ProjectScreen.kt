package com.comp.lyricsapp.presentation.screens.project

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.text.input.ImeAction
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.comp.lyricsapp.domain.entities.Bar
import com.comp.lyricsapp.domain.entities.Line
import com.comp.lyricsapp.presentation.theme.LightPrimary
import com.comp.lyricsapp.presentation.theme.Typography
import com.comp.lyricsapp.presentation.view_models.BarViewModel
import com.comp.lyricsapp.presentation.view_models.LineViewModel
import com.comp.lyricsapp.presentation.view_models.ProjectViewModel
import com.comp.lyricsapp.utils.formatTimestamp

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

    var projectBars by remember { mutableStateOf<List<Bar>>(emptyList()) }

    LaunchedEffect(projectId) {
        projectId?.let { id ->
            projectViewModel.getProjectWithBars(id)
        }
    }

    // ✅ Keeps the UI updated when the project state changes
    LaunchedEffect(savedProjectWithBars) {
        savedProjectWithBars?.let {
            projectTitle = it.project.title
            projectBars = it.bars
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
                                        savedProjectWithBars?.let {
                                            it.project.title = savedProjectTitle
                                            projectViewModel.updateProject(it.project)
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
        },
        bottomBar = {
            var newStandaloneLines by remember { mutableStateOf(listOf<String>()) }
            LineBottomEditor(
                onSave = {
                    projectId?.let {
                        barViewModel.createBar(Bar(0, projectId), onResult = { result ->
                            newStandaloneLines.forEach { line ->
                                lineViewModel.createLine(
                                    Line(
                                        id = 0,
                                        barId = result.data,
                                        line = line,
                                        timestamp = formatTimestamp(System.currentTimeMillis())
                                    )
                                )
                            }

                        })
                    }
                },
                onLineAdded = { line ->
                    newStandaloneLines += line
                }
            )
        }
    ){ innerPadding ->
        Box(
            modifier = Modifier.padding(innerPadding).fillMaxSize()
        ) {
            ProjectWorkBoardWorkBoard(
                projectBars = projectBars
            )
        }
    }
}

@Composable
fun LineBottomEditor(
    onSave: () -> Unit,
    onLineAdded: (String) -> Unit
){

    var newStandaloneLine by remember { mutableStateOf("") }

    val focusManager = LocalFocusManager.current  // Manages focus
    val keyboardController = LocalSoftwareKeyboardController.current  // Manages keyboard
    val focusRequester = remember { FocusRequester() } // Focus controller

    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .focusRequester(focusRequester)
        ,
        value = newStandaloneLine,
        onValueChange = {
                newValue -> newStandaloneLine = newValue
        },
        placeholder = { Text("Hit it", style = Typography.body2, color = Color.White) },
        singleLine = false,
        textStyle = Typography.body2,
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        ),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = LightPrimary,
            textColor = Color.White,
            cursorColor = Color.White,
            focusedIndicatorColor = LightPrimary,  // 👈 Removes line when focused
            unfocusedIndicatorColor = Color.Transparent // 👈 Removes line when unfocused

        ),
        keyboardActions = KeyboardActions(onDone = {
            onLineAdded(newStandaloneLine)
            newStandaloneLine = ""
            focusManager.clearFocus()  // Remove focus from TextField
            keyboardController?.hide()  // Hide keyboard
        }),
        trailingIcon = {
            IconButton(
                onClick = {
                    onLineAdded(newStandaloneLine)
                    newStandaloneLine = ""
                    focusManager.clearFocus()  // Move focus to TextField
                    keyboardController?.hide()
                    onSave()
                }
            ) {
                Icon(imageVector = Icons.Default.Save, contentDescription = "Save lines", tint = Color.White)
            }
        }
    )
}

@Composable
fun ProjectWorkBoardWorkBoard(
    projectBars: List<Bar>
){

    Log.d("WorkBoard", projectBars.toString())
    val barIds = projectBars.map { it.id }
    BarContainer(
        barIds = barIds.toTypedArray()
    )

}
