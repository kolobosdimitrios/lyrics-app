package com.comp.lyricsapp.presentation.components

import android.util.Log
import android.widget.Toast
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.comp.lyricsapp.presentation.theme.LightBackground
import kotlinx.coroutines.launch
import kotlin.math.abs
import kotlin.math.roundToInt

@Composable
private fun CustomBox(
    backgroundColor: Color,
    padding: PaddingValues,
    shape: CornerBasedShape = RoundedCornerShape(8.dp),
    contentPadding: PaddingValues = PaddingValues(16.dp),
    content: @Composable (PaddingValues) -> Unit    // <-- This is the key!
){

    Box(modifier = Modifier
        .padding(padding)
        .fillMaxWidth()
        .wrapContentHeight(Alignment.CenterVertically)
        .background(color =backgroundColor, shape = shape)
    ){
        content(contentPadding)
    }
}


@Composable
fun SwipeToDeleteBox(
    modifier: Modifier = Modifier,
    onDelete: () -> Unit,
    content: @Composable () -> Unit
) {
    val swipeThreshold = 60.dp
    var offsetX by remember { mutableStateOf(0f) }
    val scope = rememberCoroutineScope()

    // For dp to px
    val density = LocalDensity.current
    val swipeThresholdPx = with(density) { swipeThreshold.toPx() }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        // Background (delete icon), always present at z=0
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(MaterialTheme.colors.error, shape = RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.CenterEnd
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete",
                tint = Color.White,
                modifier = Modifier
                    .padding(end = 24.dp)
                    .size(24.dp)
            )
        }

        // Foreground (draggable item)

        Box(
            modifier = Modifier
                .offset { IntOffset(offsetX.roundToInt(), 0) }
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectHorizontalDragGestures(
                        onHorizontalDrag = { _, dragAmount ->
                            offsetX = (offsetX + dragAmount).coerceIn(-swipeThresholdPx * 2, 0f)
                        },
                        onDragEnd = {
                            scope.launch {
                                if (abs(offsetX) > swipeThresholdPx) {
                                    // Animate out and trigger delete
                                    animate(
                                        initialValue = offsetX,
                                        targetValue = -swipeThresholdPx * 2,
                                        animationSpec = tween(200)
                                    ) { value, _ ->
                                        offsetX = value
                                    }
                                    onDelete()
                                } else {
                                    // Animate back
                                    animate(
                                        initialValue = offsetX,
                                        targetValue = 0f,
                                        animationSpec = tween(200)
                                    ) { value, _ ->
                                        offsetX = value
                                    }
                                }
                            }
                        }
                    )
                }
        ) {
            content()
        }
    }
}

@Composable
private fun TestSwipeToDelete(){
    val items = remember { mutableStateListOf("One", "Two", "Three") }

    LazyColumn {
        items(items, key = { it }) { item ->
            SwipeToDeleteBox(
                onDelete = { items.remove(item) }
            ) {
                Text(
                    text = item,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }
            Spacer(Modifier.height(8.dp))
        }
    }

}

// Helper for dp.toPx inside composables
@Composable
private fun Dp.toPx(): Float {
    val density = LocalDensity.current
    return with(density) { this@toPx.toPx() }
}


@Composable
fun CustomBarBox(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    padding: PaddingValues = PaddingValues(8.dp),
    shape: CornerBasedShape = RoundedCornerShape(8.dp),
    contentPadding: PaddingValues,
    content: @Composable (PaddingValues) -> Unit
) {
    Box(
        modifier = Modifier
            .padding(padding)
            .fillMaxWidth()
            .wrapContentHeight()
            .background(color = backgroundColor, shape = shape)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp) // Adjust this value as needed for your button size
        ) {
            content(contentPadding)
        }
    }
}


@Composable
fun CustomBox_Preview(){
    CustomBox(
        backgroundColor = LightBackground,
        padding = PaddingValues(8.dp),
    ) { innerPadding ->

        Text(
            modifier = Modifier.padding(innerPadding),
            text = "Hello World"
        )
    }
}