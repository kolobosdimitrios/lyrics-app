package com.comp.lyricsapp.presentation.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.comp.lyricsapp.presentation.theme.DarkColorPalette
import com.comp.lyricsapp.presentation.theme.LightColorPalette

@Composable
fun CustomIconButton(
    title: String,
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    shape: CornerBasedShape = RoundedCornerShape(8.dp),
    colors: ButtonColors = ButtonDefaults.buttonColors(
        contentColor = MaterialTheme.colors.onPrimary
    ),
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = shape,
        colors = colors,
        contentPadding = contentPadding
    ) {
        Icon(
            imageVector = icon,
            contentDescription = title,
            modifier = Modifier.size(20.dp)
        )
        Spacer(Modifier.width(8.dp))
        Text(
            text = title,
            style = MaterialTheme.typography.body2
        )
    }
}

@Preview(showBackground = true, name = "Light Mode")
@Composable
fun PreviewUserPaletteIconButton_Light() {
    MaterialTheme(
       colors = LightColorPalette
    ) {
        Surface(modifier = Modifier.padding(16.dp)) {
            CustomIconButton(
                title  = "Send",
                icon   = Icons.Default.Send,
                onClick= { /* preview click */ }
            )
        }
    }
}

@Preview(showBackground = true, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES, name = "Dark Mode")
@Composable
fun PreviewUserPaletteIconButton_Dark() {
    MaterialTheme(
        colors = DarkColorPalette
    ) {
        Surface(modifier = Modifier.padding(16.dp)) {
            CustomIconButton(
                title  = "Send",
                icon   = Icons.Default.Send,
                onClick= { /* preview click */ }
            )
        }
    }
}
