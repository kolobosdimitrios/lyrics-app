package com.comp.lyricsapp.presentation.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat.getString
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.comp.lyricsapp.R
import com.comp.lyricsapp.presentation.navigation.Screen
import com.comp.lyricsapp.presentation.theme.MyAppTheme
import com.comp.lyricsapp.presentation.theme.Typography


@Composable
fun HomeScreen(navController: NavController) {

    Scaffold(
        topBar = {
            TopBar(
                getString(
                    LocalContext.current,
                    R.string.app_name
                ),
                navController
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
fun TopBar(appTitle: String = "Lyrics", navController: NavController) {

    MyAppTheme {
        TopAppBar(
            title = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ){

                    Text(
                        style = Typography.h1,
                        text = appTitle
                    )
                    IconButton(
                        onClick = {
                            navController.navigate(Screen.Project.route)
                        }
                    ) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = "Add new Project")
                    }
                }
            }
        )
    }
}


