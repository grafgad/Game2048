package com.example.game2048.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.game2048.R
import com.example.game2048.presentation.GameViewModel
import com.example.game2048.presentation.theme.GameColors


@Composable
fun GameOverScreen(
    modifier: Modifier = Modifier,
    viewModel: GameViewModel
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(GameColors.Black.copy(alpha = 0.5f)),
        contentAlignment = Alignment.Center
    ) {
        Column(modifier = Modifier) {
            Text(
                text = stringResource(id = R.string.game_over),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                color = GameColors.Yellow,
                fontSize = 40.sp,
                textAlign = TextAlign.Center,
            )
            Text(
                text = stringResource(id = R.string.next_time),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                color = GameColors.Yellow,
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
            Button(
                onClick = {
                    viewModel.newGame()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 80.dp, vertical = 10.dp)
            ) {
                Text(
                    text = "Try again",
                    color = GameColors.Yellow,
                    fontSize = 20.sp
                )
            }
        }
    }
}

@Preview
@Composable
fun GameOverScreenPreview() {
    GameOverScreen(
        modifier = Modifier,
        viewModel = GameViewModel()
    )
}