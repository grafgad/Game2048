package com.example.game2048

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.game2048.presentation.ui.GameScreen
import com.example.game2048.presentation.theme.Game2048Theme
import com.example.game2048.presentation.theme.GameTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Game2048Theme {
                // A surface container using the "background" color from the theme
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    drawerBackgroundColor = GameTheme.color.background
                ) {
                    GameScreen(
                        modifier = Modifier.padding(it)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Game2048Theme {
        GameScreen()
    }
}