package com.example.game2048

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.game2048.ui.theme.GameColors

private const val TEXT_SCALE_REDUCTION_INTERVAL = 0.9f

@Composable
fun Arena(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize()
            .aspectRatio(1f)
            .background(GameColors.Grey)
    ) {
        Column(
            modifier = Modifier
        ) {
            for (k in 0 until 4) {
                HorizontalLine(
                    value = Fields().vertLine[k]
                )
            }
        }
    }
}

@Composable
fun HorizontalLine(
    modifier: Modifier = Modifier,
    value: Array<Int> = arrayOf()
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        Arrangement.SpaceAround
    ) {
        for (i in 0 until 4) {
            Field(
                value = value[i],
                textStyle = TextStyle(fontSize = 38.sp),
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .padding(3.dp)
                    .background(GameColors.Yellow),
            )
        }
    }
}

@Composable
fun Field(
    value: Int?,
    textStyle: TextStyle,
    modifier: Modifier = Modifier,
    targetTextSizeHeight: TextUnit = textStyle.fontSize,
    maxLines: Int = 1,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        var textSize by remember { mutableStateOf(targetTextSizeHeight) }

        Text(
            text = value?.toString() ?: "",
            modifier = Modifier,
            fontSize = textSize,
            overflow = TextOverflow.Ellipsis,
            maxLines = maxLines,
            onTextLayout = { textLayoutResult ->
                val maxCurrentLineIndex: Int = textLayoutResult.lineCount - 1

                if (textLayoutResult.isLineEllipsized(maxCurrentLineIndex)) {
                    textSize = textSize.times(TEXT_SCALE_REDUCTION_INTERVAL)
                }
            },
        )
    }
}


@Preview(widthDp = 200, heightDp = 320)
@Composable
fun ArenaPreview320() {
    Arena()
}

@Preview
@Composable
fun ArenaPReview() {
    Arena()
}