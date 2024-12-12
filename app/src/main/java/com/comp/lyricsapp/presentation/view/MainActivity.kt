package com.comp.lyricsapp.presentation.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import com.comp.lyricsapp.presentation.navigation.Navigation
import com.comp.lyricsapp.presentation.theme.MyAppTheme
import com.comp.lyricsapp.presentation.view.home_screen.DashBoard
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyAppTheme {
                Navigation()
            }
        }


    }

}






