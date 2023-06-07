package com.example.game2048.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.game2048.data.Directions
import com.example.game2048.data.ROWCOUNT
import com.example.game2048.presentation.GameViewModel
import com.example.game2048.presentation.theme.GameColors
import kotlin.math.abs

@Composable
fun Arena(
    modifier: Modifier = Modifier,
    viewModel: GameViewModel
) {
    var direction by remember { mutableStateOf(Directions.UP) }
    val gameOver by viewModel.gameOver.collectAsState()
    Box(
        modifier = modifier
            .padding(16.dp)
            .aspectRatio(1f)
            .background(GameColors.Grey)
            .pointerInput(Unit) {
                detectDragGestures(
                    onDrag = { change, dragAmount ->
                        change.consume()
                        val (x, y) = dragAmount
                        when {
                            x > 5 && abs(x) > abs(y) -> {
                                direction = Directions.RIGHT
                            } // RIGHT
                            x < -5 && abs(x) > abs(y) -> {
                                direction = Directions.LEFT
                            } // LEFT
                            y > 5 && abs(x) < abs(y) -> {
                                direction = Directions.DOWN
                            } // DOWN
                            y < -5 && abs(x) < abs(y) -> {
                                direction = Directions.UP
                            } // UP
                        }
                    },
                    onDragEnd = {
                        viewModel.makeSwipe(direction)
                    },
                    onDragStart = {},
                    onDragCancel = {}
                )
            }
    ) {
        val game by viewModel.game.collectAsState()
        Column {
            repeat(ROWCOUNT) { row ->
                HorizontalFields(
                    modifier = Modifier,
                    line = game.matrix.squareMatrix()[row]
                )
            }
        }
        if (gameOver) {
            GameOverScreen(
                modifier = Modifier,
                viewModel = viewModel
            )
        }
    }
}

@Composable
fun HorizontalFields(
    modifier: Modifier = Modifier,
    line: List<Int?> = listOf()
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
                digit = line[digit],
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
