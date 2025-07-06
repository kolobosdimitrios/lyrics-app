package com.comp.lyricsapp.presentation.screens.project

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.comp.lyricsapp.domain.entities.Bar
import com.comp.lyricsapp.domain.entities.Line
import com.comp.lyricsapp.presentation.components.CustomIconButton
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
                        modifier = Modifier
                            .fillMaxWidth()
                            .navigationBarsPadding()
                            .focusRequester(focusRequester),
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

            BottomEditorView(
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
        },
        modifier = Modifier.fillMaxSize().navigationBarsPadding()
    ){ innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            ProjectWorkBoard(
                projectBars = projectBars,
                focusManager = focusManager,
                keyboardController = keyboardController,
                focusRequester = focusRequester
            )
        }
    }
}

@Composable
fun BottomEditorView(
    onCreateBar: () -> Unit,
    onAddLineToBar: (String) -> Unit
) {
    var newStandaloneLine by remember { mutableStateOf("") }

    val focusRequester = remember { FocusRequester() } // Focus controller

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalAlignment = Alignment.Start,

    ) {
        BarInputTextView(
            focusRequester = focusRequester,
            onValueChanged = {
                    newLine -> newStandaloneLine = newLine
            }
        )

        Spacer(
            modifier = Modifier.width(8.dp),
        )

        UserCommandPaletteView(
            textState = newStandaloneLine,
            onAddLineToBar = onAddLineToBar,
            onCreateBar = onCreateBar
        )


    }
}

@Composable
fun BarInputTextView(
    focusRequester: FocusRequester,
    onValueChanged: (String) -> Unit
){
    var newStandaloneLine by remember { mutableStateOf("") }
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .focusRequester(focusRequester)
            .padding(horizontal = 8.dp),
        value = newStandaloneLine,
        onValueChange = {
            newStandaloneLine = it
            onValueChanged(it)
        },
        placeholder = { Text("Hit it", style = Typography.body2, color = MaterialTheme.colors.primary) },
        singleLine = false,
        textStyle = Typography.body2,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.background,
            textColor = MaterialTheme.colors.primary,
            cursorColor = MaterialTheme.colors.primary,
            focusedIndicatorColor = Color.White,  // 👈 Removes line when focused
            unfocusedIndicatorColor = MaterialTheme.colors.primary

        )
    )
}


@Composable
fun UserCommandPaletteView(
    textState: String,
    onAddLineToBar: (String) -> Unit,
    onCreateBar: () -> Unit
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .imePadding()                                 // ← pushes this row above the IME
            .navigationBarsPadding()              // ← if you need nav‐bar safety too
            .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        CustomIconButton(
            title = "Add Line",
            icon = Icons.Default.ArrowUpward,
            onClick = {
                if(textState.isNotBlank()) {
                    onAddLineToBar(textState)
                }
            },
            modifier = Modifier.weight(1f)
        )

        CustomIconButton(
            title = "Create Bar",
            icon = Icons.Default.Add,
            onClick = {
                onCreateBar()
            },
            modifier = Modifier.weight(1f)
        )
    }

}

@Composable
fun ProjectWorkBoard(
    projectBars: List<Bar>,
    focusManager: FocusManager,
    keyboardController: SoftwareKeyboardController?,
    focusRequester: FocusRequester
){

    Log.d("WorkBoard", projectBars.toString())
    val barIds = projectBars.map { it.id }
    BarContainer(
        barIds = barIds.toTypedArray(),
        focusRequester = focusRequester,
        focusManager = focusManager,
        keyboardController = keyboardController
    )

}
