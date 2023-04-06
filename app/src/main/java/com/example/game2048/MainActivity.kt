package com.example.game2048

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.game2048.ui.GameScreen
import com.example.game2048.ui.theme.Game2048Theme
import com.example.game2048.ui.theme.GameColors

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Game2048Theme {
                // A surface container using the "background" color from the theme
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    drawerBackgroundColor = MaterialTheme.colors.background
                ) {
                    GameScreen(
                        modifier = Modifier.padding(it)
                            .background(GameColors.Purple200)
                    )
                    onSwipe()
                }
            }
        }
    }
}

private fun onSwipe() {
    for (i in 0 until 4) {

    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Game2048Theme {
        GameScreen()
    }
}