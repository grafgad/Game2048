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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.game2048.ui.theme.GameColors

private const val TEXT_SCALE_REDUCTION_INTERVAL = 0.9f

@Composable
fun Tile(
    modifier: Modifier = Modifier,
    value: Int?
) {
    val backgroundColor = tileColor(value)

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
    style: TextStyle = TextStyle(fontSize = 50.sp)
) {
    var resizedTextStyle by remember { mutableStateOf(style) }

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

fun tileColor(value: Int?): Color {
    val color = when (value) {
        2 -> GameColors.colorFor_2
        4 -> GameColors.colorFor_4
        8 -> GameColors.colorFor_8
        16 -> GameColors.colorFor_16
        32 -> GameColors.colorFor_32
        64 -> GameColors.colorFor_64
        128 -> GameColors.colorFor_128
        256 -> GameColors.colorFor_256
        512 -> GameColors.colorFor_512
        1024 -> GameColors.colorFor_1024
        2048 -> GameColors.colorFor_2048
        4096 -> GameColors.colorFor_4096
        8192 -> GameColors.colorFor_8192
        16384 -> GameColors.colorFor_16384
        32768 -> GameColors.colorFor_32768
        65536 -> GameColors.colorFor_65536
        5 -> GameColors.Yellow
        else -> GameColors.colorFor_16384
    }
    return color
}