package com.example.game2048

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.game2048.ui.theme.GameColors

@Composable
fun Arena(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier

            .padding(16.dp)
            .aspectRatio(1f)
            .background(GameColors.Grey)
    ) {
        Column {
            for (i in 0 until 4) {
                HorizontalFields(
                    value = Fields().vertLine[i]
                )
            }
        }
    }
}

@Composable
fun HorizontalFields(
    modifier: Modifier = Modifier,
    value: Array<Int?> = arrayOf()
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        Arrangement.SpaceAround
    ) {
        for (i in 0 until 4) {
            Tile(
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .padding(3.dp)
                    .background(GameColors.Yellow),
                value = value[i],
                textStyle = TextStyle(fontSize = 50.sp),
            )
        }
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