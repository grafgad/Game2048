package com.example.game2048.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private const val TEXT_SCALE_REDUCTION_INTERVAL = 0.9f

@Composable
fun Tile(
    modifier: Modifier = Modifier,
    value: Int?,
    textStyle: TextStyle = TextStyle(fontSize = 50.sp),
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        var textSize by remember { mutableStateOf(textStyle.fontSize) }

        Text(
            text = value?.toString() ?: "",
            modifier = Modifier.padding(2.dp),
            fontSize = textSize,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            onTextLayout = { textLayoutResult ->
                val maxCurrentLineIndex: Int = textLayoutResult.lineCount - 1

                if (textLayoutResult.isLineEllipsized(maxCurrentLineIndex)) {
                    textSize = textSize.times(TEXT_SCALE_REDUCTION_INTERVAL)
                }
            },
        )
    }
}