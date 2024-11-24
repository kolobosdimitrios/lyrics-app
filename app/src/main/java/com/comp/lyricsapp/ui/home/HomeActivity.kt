package com.comp.lyricsapp.ui.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.comp.lyricsapp.R
import com.comp.lyricsapp.ui.theme.MyAppTheme
import com.comp.lyricsapp.ui.theme.fontFamily
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyAppTheme {
                //TODO: Make a dashboard!!
                InfoBoxGrid()
            }
        }


    }

}

@Composable
private fun DashBoard(){


}

@Preview(device = "spec:parent=pixel_fold")
@Composable
private fun InfoBoxGrid(){

    LazyHorizontalStaggeredGrid(
        rows = StaggeredGridCells.Fixed(2),
        modifier = Modifier.fillMaxWidth().fillMaxHeight(0.45f),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalItemSpacing = 8.dp
    ) {
        val items = (1..6).map {
            ItemTest("This is an item", "This is a body for that item for test purposes", "Created@13:00")
        }

        items(items) {item ->
            InfoBox(
                backgroundColor1 = colorResource(R.color.colorPrimary),
                backgroundColor2 = colorResource(R.color.colorPrimaryDark),
                header = item.title,
                body = item.body,
                createdAt = item.createdAt
            )
        }
    }

}

@Composable
private fun InfoBox(backgroundColor2: Color, backgroundColor1: Color, header: String, body: String, createdAt: String){

    Card(
        modifier = Modifier.size(150.dp, 200.dp)
    ){

        Box (
            modifier = Modifier.fillMaxWidth()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            backgroundColor1,
                            backgroundColor2
                        ),
                        startY = 100f
                    )
                ),
            contentAlignment = Alignment.TopStart,
        ){

            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.padding(8.dp).fillMaxSize(),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = header,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start
                )
                Text(
                    text = body,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    modifier = Modifier
                        .padding(3.dp),
                    textAlign = TextAlign.Start
                )
                Text(
                    text = createdAt,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 10.sp,
                    textAlign = TextAlign.Start
                )
            }

        } //header row


    }

}

@Composable
private fun InfoRow(){

}


data class ItemTest(
    val title: String,
    val body: String,
    val createdAt: String
)





