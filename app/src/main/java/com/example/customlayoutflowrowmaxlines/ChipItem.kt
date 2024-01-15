package com.example.customlayoutflowrowmaxlines

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun ChipItem(
    text: String,
    modifier: Modifier = Modifier,
    borderColor: Color = MaterialTheme.colors.primary,
    backgroundColor: Color = Color.Transparent,
    borderWidth: Int = 2,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .background(backgroundColor, RoundedCornerShape(percent = 50))
            .padding(8.dp)
            .border(
                width = borderWidth.dp,
                color = borderColor,
                shape = RoundedCornerShape(percent = 50)
            )
            .clickable { onClick.invoke() }
    ) {
        Text(
            text = text,
            modifier = Modifier
                .padding(PaddingValues(vertical = 16.dp, horizontal = 8.dp)),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.body1
        )
    }
}
