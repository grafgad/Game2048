package com.example.game2048.presentation

import androidx.lifecycle.ViewModel
import com.example.game2048.data.Directions
import com.example.game2048.data.Game
import com.example.game2048.data.MyGame
import com.example.game2048.data.TileData
import com.example.game2048.data.TileShift
import com.example.game2048.domain.GameOverCheck
import com.example.game2048.domain.Swipes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GameViewModel : ViewModel() {

    private var _game = MutableStateFlow(MyGame.myUniqueGame)
    val game: StateFlow<Game> = _game.asStateFlow()

    private var _gameOver = MutableStateFlow(false)
    val gameOver: StateFlow<Boolean> = _gameOver

    private var _tileShift = MutableStateFlow(TileShift)
    val tileShift: StateFlow<TileShift> = _tileShift

    private val lastMove = MutableStateFlow(Game()) // предыдущий ход для возврата
    private var undoAvailable: Boolean = true
    private var moveScore = 0 // число очков за ход
    private var previousMoveScore = 0 //число очков для отмены хода

    fun newGame(): Game {
        _gameOver.update { false }
        val position = (0 until _game.value.matrix.array.size).random()
        val digit = selectRandomDigit()
        _game.value.matrix.array.replaceAll { TileData(null, 0) }
        val temporalArray = _game.value.matrix.array.toMutableList()
        temporalArray[position] = digit
        _game.update { game ->
            game.copy(
                matrix = _game.value.matrix.matrixCopy(temporalArray),
                score = 0,
                move = 0
            )
        }
        previousMoveScore = 0
        return _game.value
    }

    fun undoMove(): Game {
        if (undoAvailable) {
            _game.update {
                it.copy(
                    matrix = lastMove.value.matrix,
                    score = lastMove.value.score,
                    move = lastMove.value.move
                )
            }
            undoAvailable = false
        }
        _gameOver.update { false }
        return _game.value
    }

    fun setMoveScore(score: Int) {
        moveScore += score // если за ход несколько плиток суммируется, то нужна их сумма.
    }

    fun setTileShift(position: Int, digit: Int?, shift: Int): MutableMap<Int, TileData> {
        _tileShift.value.tileShift[position] = TileData(digit, shift)
        return _tileShift.value.tileShift
    }

    fun makeSwipe(direction: Directions): Game {
        val temporalArray = _game.value.matrix.array.toMutableList()
        swipeToDirection(direction, temporalArray)
        usualMove(temporalArray)
        return _game.value
    }

    private fun swipeToDirection(
        direction: Directions,
        array: MutableList<TileData>
    ): MutableList<TileData> {
        when (direction) {
            Directions.RIGHT -> Swipes(this).swipeToRight(array)
            Directions.LEFT -> Swipes(this).swipeToLeft(array)
            Directions.UP -> Swipes(this).swipeToUp(array)
            Directions.DOWN -> Swipes(this).swipeToDown(array)
            Directions.NONE -> {}
        }
        return array
    }

    private fun usualMove(array: MutableList<TileData>) {
        if (noTilesChange(array)) return
        lastMove.value = _game.value
        addNewDigit(array)
        _game.update { game ->
            game.copy(
                matrix = _game.value.matrix.matrixCopy(array),
                score = _game.value.score.plus(moveScore),
                move = _game.value.move.plus(1),
            )
        }
        undoAvailable = true
        previousMoveScore = moveScore
        moveScore = 0
        canContinue(_game.value.matrix.array)
    }

    private fun noTilesChange(temporalArray: MutableList<TileData>): Boolean {
        return temporalArray == _game.value.matrix.array
    }

    private fun addNewDigit(array: MutableList<TileData>): MutableList<TileData> {
        val tempArr = mutableListOf<Int>()
        repeat(array.size) {
            if (array[it].digit == null) tempArr.add(it)
        }
        if (tempArr.isNotEmpty()) {
            val position = tempArr.random()
            array[position] = selectRandomDigit()
        }
        return array
    }

    private fun selectRandomDigit(): TileData {
        val a = (0..100).random()
        return if (a < 85) TileData(2,0 ) else TileData(4,0)
    }

    private fun canContinue(array: MutableList<TileData>) {
        if (!GameOverCheck().canContinue(array)) {
            _gameOver.update { true }
        }
    }
}