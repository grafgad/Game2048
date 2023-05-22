package com.example.game2048.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.game2048.ui.theme.GameColors
import com.example.game2048.ui.theme.tileColor

private const val TEXT_SCALE_REDUCTION_INTERVAL = 0.9f

@Composable
fun Tile(
    modifier: Modifier = Modifier,
    value: Int?
) {
    val backgroundColor = GameColors.tileColor(value)

    Box(
        modifier = modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(5.dp))
            .background(color = backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        ResizedText(text = value?.toString() ?: "")
    }
}

@Composable
fun ResizedText(
    text: String,
    style: TextStyle = TextStyle(fontSize = 42.sp)
) {
    var resizedTextStyle by remember (text) { mutableStateOf(style) }

    Text(
        text = text,
        modifier = Modifier.padding(2.dp),
        softWrap = false,
        fontSize = resizedTextStyle.fontSize,
        onTextLayout = { textLayoutResult ->
            if (textLayoutResult.didOverflowWidth) {
                resizedTextStyle = resizedTextStyle.copy(
                    fontSize = resizedTextStyle.fontSize.times(TEXT_SCALE_REDUCTION_INTERVAL)
                )
            }
        }
    )
}