package com.example.game2048.ui

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.game2048.GameViewModel
import com.example.game2048.R

@Composable
fun GameScreen(
    modifier: Modifier = Modifier,
    viewModel: GameViewModel = viewModel()
) {
    val movesCount = viewModel.movesCount.collectAsState().value
    val scores by viewModel.gameScore.collectAsState()
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Statistics(
            modifier = Modifier,
            movesCount = movesCount,
            scores = scores
        )
        Buttons(
            modifier = Modifier,
            viewModel = viewModel
        )
        Arena(viewModel = viewModel)
        SwipeButtons(
            modifier,
            viewModel
        )
    }
}

@Composable
fun Statistics(
    modifier: Modifier = Modifier,
    movesCount: Int,
    scores: Int
) {

    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Text(
            text = (stringResource(id = R.string.moves) + movesCount.toString()),
            modifier = Modifier.padding(2.dp),
            fontSize = 20.sp,
        )
        Text(
            text = (stringResource(id = R.string.scores) + scores.toString()),
            modifier = Modifier.padding(2.dp),
            fontSize = 20.sp,
        )
    }
}

@Composable
fun Buttons(
    modifier: Modifier = Modifier,
    viewModel: GameViewModel,
) {

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = {
                viewModel.undoMove()
                Log.d("DDDDDD", "undo ${viewModel.matrix.value.asMatrix()}")
            },
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.baseline_undo_24),
                contentDescription = "Undo"
            )
        }
        Button(
            onClick = {
                viewModel.newGame()
            }
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.baseline_autorenew_24),
                contentDescription = "Start new game",
                modifier = Modifier
                    .padding(end = 10.dp)
                    .rotate(90f)
            )
            Text(text = stringResource(id = R.string.start_new_game))
        }
    }
}

@Composable
fun SwipeButtons(
    modifier: Modifier = Modifier,
    viewModel: GameViewModel,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                viewModel.swipeToUp()
                Log.d("DDDDDD", "up ${viewModel.matrix.value.asMatrix()}")
            }
        ) {
            Text(text = "UP")
        }
        Row {
            Button(
                onClick = {
                    viewModel.swipeToLeft()
                    Log.d("DDDDDD", "left ${viewModel.matrix.value.asMatrix()}")
                },
                modifier = Modifier.padding(horizontal = 20.dp)
            ) {
                Text(text = "LEFT")
            }
            Button(
                onClick = {
                    viewModel.swipeToRight()
                    Log.d("DDDDDD", "right ${viewModel.matrix.value.asMatrix()}")
                },
                modifier = Modifier.padding(horizontal = 20.dp)
            ) {
                Text(text = "RIGHT")
            }
        }
        Button(
            onClick = {
                viewModel.swipeToDown()
                Log.d("DDDDDD", "down ${viewModel.matrix.value.asMatrix()}")
            }
        ) {
            Text(text = "DOWN")
        }
    }
}

@Preview
@Composable
fun GameScreenPreview() {
    GameScreen()
}