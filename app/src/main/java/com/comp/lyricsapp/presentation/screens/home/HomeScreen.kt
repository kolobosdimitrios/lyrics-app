package com.comp.lyricsapp.presentation.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat.getString
import androidx.navigation.NavController
import com.comp.lyricsapp.R
import com.comp.lyricsapp.presentation.navigation.Screen


@Composable
fun HomeScreen(navController: NavController) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(getString(LocalContext.current, R.string.app_name))
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = MaterialTheme.colors.onPrimary,
                onClick = {
                    navController.navigate(Screen.Project.route)
                }
            ) { Icon(Icons.Default.Add, contentDescription = "Add") }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            DashBoard(navController)
        }
    }

}