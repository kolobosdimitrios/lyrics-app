package com.comp.lyricsapp.presentation.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.comp.lyricsapp.presentation.navigation.Navigation
import com.comp.lyricsapp.presentation.theme.MyAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // let the system draw behind the status/nav bars
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            MyAppTheme {
                Navigation()
            }
        }


    }

}






