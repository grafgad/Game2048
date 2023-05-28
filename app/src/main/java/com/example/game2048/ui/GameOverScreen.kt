package com.example.game2048.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.game2048.GameViewModel
import com.example.game2048.R
import com.example.game2048.ui.theme.GameColors


@Composable
fun GameOverScreen(
    modifier: Modifier = Modifier,
    isGameOver: Boolean,
    viewModel: GameViewModel
) {
    if (isGameOver) {
        AlertDialog(
            modifier = modifier
                .fillMaxWidth(),
            title = {
                Text(
                    text = stringResource(id = R.string.game_over),
                    modifier.fillMaxWidth(),
                    fontSize = 40.sp,
                    textAlign = TextAlign.Center,
                )
            },
            text = {
                Text(
                    text = stringResource(id = R.string.next_time),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp
                )
            },
            backgroundColor = Color.Transparent,
            contentColor = GameColors.Yellow,
            buttons = {
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 80.dp),
                    onClick = {
                        viewModel.newGame()
                    }
                ) {
                    Text(
                        text = "Try again",
                        fontSize = 20.sp,
                        color = GameColors.Yellow
                    )
                }
            },
            onDismissRequest = { },
        )
    }
}