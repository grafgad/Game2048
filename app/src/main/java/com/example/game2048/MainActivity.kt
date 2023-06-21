package com.example.game2048

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.game2048.data.Game
import com.example.game2048.data.MyGame
import com.example.game2048.presentation.GameViewModel
import com.example.game2048.presentation.theme.Game2048Theme
import com.example.game2048.presentation.theme.GameTheme
import com.example.game2048.presentation.ui.GameScreen
import com.google.gson.Gson

class MainActivity : ComponentActivity() {

    private val viewModel: GameViewModel by viewModels()
    private val sharedPreferences by lazy {
        getSharedPreferences("MY_PREFERENCE_NAME", Context.MODE_PRIVATE)
    }

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

    override fun onStart() {
        val myDataJson = sharedPreferences.getString("MY_DATA_KEY", null)
        val myData = Gson().fromJson(myDataJson, Game::class.java)
        if (myData != null) {
            MyGame.myUniqueGame = myData
        } else MyGame.myUniqueGame = viewModel.newGame()
        super.onStart()
    }

    override fun onPause() {
        with(sharedPreferences.edit()) {
            putString("MY_DATA_KEY", Gson().toJson(viewModel.game.value))
            apply()
        }
        super.onPause()
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Game2048Theme {
        GameScreen()
    }
}