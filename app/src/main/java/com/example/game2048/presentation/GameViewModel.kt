package com.example.game2048.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.game2048.data.Directions
import com.example.game2048.data.Game
import com.example.game2048.data.TileData
import com.example.game2048.domain.GameOverCheck
import com.example.game2048.domain.Swipes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GameViewModel : ViewModel() {

    private var _game = MutableStateFlow(Game())
    val game: StateFlow<Game> = _game.asStateFlow()

    private var _gameOver = MutableStateFlow(false)
    val gameOver: StateFlow<Boolean> = _gameOver

    private val lastMoveTilePosotions = MutableStateFlow(Game()) // предыдущий ход для возврата
    private var tileData = MutableStateFlow(TileData())
    private var canUseUndo: Boolean = true
    private var moveScore = 0 // число очков за ход
    private var previousMoveScore = 0 //число очков для отмены хода

    fun newGame(): Game {
        _gameOver.update { false }
        val position = (0 until _game.value.matrix.array.size).random()
        val value = selectRandomDigit()
        _game.value.matrix.array.replaceAll { null }
        val temporalArray = _game.value.matrix.array.toMutableList()
        temporalArray[position] = value
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
        if (canUseUndo) {
            _game.update {
                it.copy(
                    matrix = lastMoveTilePosotions.value.matrix,
                    score = lastMoveTilePosotions.value.score,
                    move = lastMoveTilePosotions.value.move
                )
            }
            canUseUndo = false
        }
        _gameOver.update { false }
        return _game.value
    }

    fun setMoveScore(a: Int) {
        moveScore += a // если за ход несколько плиток суммируется, то нужна их сумма.
    }

    fun setTileData(position: Int, shift: Int): MutableMap<Int, Int> {
        tileData.value.tileData[position] = shift
        return tileData.value.tileData
    }

    fun makeSwipe(direction: Directions): Game {
        val temporalArray = _game.value.matrix.array.toMutableList()
        swipeToDirection(direction, temporalArray)
        usualMove(temporalArray)
        return _game.value
    }

    private fun swipeToDirection(
        direction: Directions,
        array: MutableList<Int?>
    ): MutableList<Int?> {
        when (direction) {
            Directions.RIGHT -> Swipes(this).swipeToRight(array)
            Directions.LEFT -> Swipes(this).swipeToLeft(array)
            Directions.UP -> Swipes(this).swipeToUp(array)
            Directions.DOWN -> Swipes(this).swipeToDown(array)
        }
        return array
    }

    private fun usualMove(array: MutableList<Int?>) {
        if (noTilesChange(array)) return
        lastMoveTilePosotions.value = _game.value
        addNewDigit(array)
        _game.update { game ->
            game.copy(
                matrix = _game.value.matrix.matrixCopy(array),
                score = _game.value.score.plus(moveScore),
                move = _game.value.move.plus(1),
            )
        }
        canUseUndo = true
        previousMoveScore = moveScore
        moveScore = 0
        canContinue(_game.value.matrix.array)
    }

    private fun noTilesChange(temporalArray: MutableList<Int?>): Boolean {
        if (temporalArray == _game.value.matrix.array) {
            Log.d("moves", "noTilesChange: NO CHANGE")
            return true
        }
        return false
    }

    private fun addNewDigit(array: MutableList<Int?>): MutableList<Int?>? {
        val tempArr = mutableListOf<Int>()
        repeat(array.size) {
            if (array[it] == null) tempArr.add(it)
        }
        if (tempArr.isNotEmpty()) {
            val position = tempArr.random()
            array[position] = selectRandomDigit()
        } else return null
        return array
    }

    private fun selectRandomDigit(): Int {
        val a = (0..100).random()
        return if (a < 85) 2 else 4
    }

    private fun canContinue(array: MutableList<Int?>) {
        if (!GameOverCheck().canContinue(array)) {
            _gameOver.update { true }
        }
    }
}