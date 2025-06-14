package com.comp.lyricsapp.presentation.screens.project

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.Alignment
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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowUpward
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
import androidx.compose.ui.unit.dp
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

    var currentBarId by remember { mutableStateOf<Long?>(null) }

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

                            IconButton(
                                onClick = {
                                    savedProjectTitle = projectTitle
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


                        },
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color.Transparent,
                            cursorColor = Color.White,
                            focusedIndicatorColor = Color.Transparent,  // 👈 Removes line when focused
                            unfocusedIndicatorColor = Color.Transparent  // 👈 Removes line when unfocused

                        ),
                        keyboardActions = KeyboardActions(onDone = {
                            savedProjectTitle = projectTitle // Save text
                            savedProjectWithBars?.let {
                                it.project.title = savedProjectTitle
                                projectViewModel.updateProject(it.project)
                            }
                            focusManager.clearFocus()  // Remove focus from TextField
                            keyboardController?.hide()  // Hide keyboard
                        })
                    )

                }
            )
        },
        bottomBar = {

            LineBottomEditor(
                onCreateBar = {
                    projectId?.let {
                        barViewModel.createBar(Bar(0, projectId), onResult = { result ->
                            currentBarId = result.data
                        })
                    }
                },
                onAddLineToBar = { newLine ->
                    currentBarId?.let { barId ->
                        lineViewModel.createLine(
                            Line(
                                id = 0,
                                barId = barId,
                                line = newLine,
                                timestamp = formatTimestamp(System.currentTimeMillis())
                            )
                        )
                    }
                }
            )
        }
    ){ innerPadding ->
        Box(
            modifier = Modifier.padding(innerPadding).wrapContentSize()
        ) {
            ProjectWorkBoard(
                projectBars = projectBars
            )


        }
    }
}

@Composable
fun LineBottomEditor(
    onCreateBar: () -> Unit,
    onAddLineToBar: (String) -> Unit
    ){

    var newStandaloneLine by remember { mutableStateOf("") }

    val focusManager = LocalFocusManager.current  // Manages focus
    val keyboardController = LocalSoftwareKeyboardController.current  // Manages keyboard
    val focusRequester = remember { FocusRequester() } // Focus controller

    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .wrapContentHeight()
    ) {

        Text(
            text = "Bars hold lines. Use '+' for a new bar and \u2191 to add a line.",
            style = Typography.body2,
            color = LightPrimary,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
        )

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester)
                .padding(horizontal = 8.dp)

            ,
            value = newStandaloneLine,
            onValueChange = {
                    newValue -> newStandaloneLine = newValue
            },
            placeholder = { Text("Hit it", style = Typography.body2, color = LightPrimary) },
            singleLine = false,
            textStyle = Typography.body2,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                textColor = LightPrimary,
                cursorColor = LightPrimary,
                focusedIndicatorColor = Color.White,  // 👈 Removes line when focused
                unfocusedIndicatorColor = LightPrimary

            )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                IconButton(
                    onClick = {
                        if (newStandaloneLine.isNotBlank()) {
                            onAddLineToBar(newStandaloneLine)
                            newStandaloneLine = ""
                            focusManager.clearFocus()
                            keyboardController?.hide()
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowUpward,
                        contentDescription = "Add line",
                        tint = LightPrimary
                    )
                }
                Text(
                    text = "Add line",
                    style = Typography.body2,
                    color = LightPrimary
                )
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                IconButton(onClick = { onCreateBar() }) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "New bar",
                        tint = LightPrimary
                    )
                }
                Text(
                    text = "New bar",
                    style = Typography.body2,
                    color = LightPrimary
                )
            }
        }
        )
    }


}

@Composable
fun ProjectWorkBoard(
    projectBars: List<Bar>
){

    Log.d("WorkBoard", projectBars.toString())
    val barIds = projectBars.map { it.id }
    BarContainer(
        barIds = barIds.toTypedArray()
    )

}
