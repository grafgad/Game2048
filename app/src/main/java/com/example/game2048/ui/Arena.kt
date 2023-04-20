package com.example.game2048.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberSwipeableState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.game2048.GameViewModel
import com.example.game2048.ROWCOUNT
import com.example.game2048.ui.theme.GameColors

enum class States {
    RIGHT,
    LEFT,
    UP,
    DOWN
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Arena(
    modifier: Modifier = Modifier,
    viewModel: GameViewModel
) {
    val swipeableState = rememberSwipeableState(initialValue = 0)
    val direction = 0f/*TODO: переделать для направления*/

    Box(
        modifier = modifier
            .padding(16.dp)
            .aspectRatio(1f)
            .background(GameColors.Grey)
//            .swipeable(
//                state = swipeableState,
//                anchors = mapOf(
//                    0f to 0,
//                    direction to 1
//                ),/*TODO: доделать*/
//                orientation = Orientation.Horizontal,/*TODO: сделать для 4-х направлений*/
//            )
    ) {
        val vmMatrix by viewModel.matrix.collectAsState()
        Column {
            repeat(ROWCOUNT) { row ->
                HorizontalFields(
                    modifier = Modifier,
                    value = vmMatrix.asMatrix()[row]
                )
            }
        }
    }
}

@Composable
fun HorizontalFields(
    modifier: Modifier = Modifier,
    value: List<Int?> = listOf()
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        Arrangement.SpaceAround
    ) {
        repeat(ROWCOUNT) { digit ->
            Tile(
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .padding(3.dp),
                value = value[digit],
            )
        }
    }
}


@Preview(widthDp = 200, heightDp = 320)
@Composable
fun ArenaPreview320() {
    Arena(viewModel = GameViewModel())
}

@Preview
@Composable
fun ArenaPReview() {
    Arena(viewModel = GameViewModel())
}