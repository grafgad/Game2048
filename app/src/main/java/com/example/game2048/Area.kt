package com.example.game2048

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.game2048.ui.theme.GameColors


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
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .padding(3.dp)
                    .background(GameColors.Yellow),
                value = value[i]
            )
        }
    }
}

@Composable
fun Field(
    value: Int?,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = value?.toString() ?: "",
            maxLines = 1,
            softWrap = false,
//            fontSize = 20.sp
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