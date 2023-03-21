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
            .background(GameColors.Grey)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize()
//                .layout { measurable, constraints ->
//                    val placeable = measurable.measure(constraints)
//                    val currentWidth = placeable.width
//                    layout(currentWidth, currentWidth) {
//                        placeable.placeRelative(0,0)
//                    }
//                }
            ,
            Arrangement.SpaceAround
        ) {
            for (i in 0 until 4) {
                Field(modifier = Modifier.weight(1f)
                    .aspectRatio(1f)
                    .padding(3.dp)
                    .background(GameColors.Yellow),
                    value = Fields().line1[i]
                )
            }

//            listOf(
//                Field(value = null),
//                Field(value = 0),
//                Field(value = 12345),
//                Field(value = 0),
//            )
        }
    }
}

@Composable
fun Field(
    value: Int?,
    modifier: Modifier = Modifier
//        .widthIn(min = 30.dp, max = 90.dp)
//        .width(35.dp)

) {
    Box(
        modifier = modifier

        ,
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text =
            value?.toString() ?: ""
        )


    }
}


@Preview(widthDp = 200, heightDp = 320)
@Composable
fun ArenaPreview() {
    Arena()
}
@Preview()
@Composable
fun ArenaPReview() {
    Arena()
}