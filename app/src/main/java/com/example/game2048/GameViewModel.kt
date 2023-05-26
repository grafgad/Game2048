package com.example.game2048

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GameViewModel : ViewModel() {

    private var _game = MutableStateFlow(Game())
    val game: StateFlow<Game> = _game.asStateFlow()

    private val lastMoveTilePosotions = MutableStateFlow(Game()) // предыдущий ход для возврата
    private var canUseUndo: Boolean = true

    private var moveScore = 0 // число очков за ход
    private var previousMoveScore = 0 //число очков для отмены хода


    fun setMoveScore(a: Int) {
        moveScore += a // если за ход несколько плиток суммируется, то нужна вся сумма.
        previousMoveScore = a
    }

    private fun usualMove(array: MutableList<Int?>) {
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
        moveScore = 0
        Log.d("moves", "newScore =  ${_game.value.move}")
        Log.d("moves", "_gameScore = ${_game.value.score}")
    }

    fun undoMove() {
        if (canUseUndo) {
            _game.value = lastMoveTilePosotions.value
            _game.value.move.minus(1)
            _game.value.score.minus(previousMoveScore)
            canUseUndo = false
        }
    }

    fun swipeToDirection(direction: Directions): Game {
        val temporalArray = game.value.matrix.array.map { it }.toMutableList()
        when (direction) {
            Directions.RIGHT -> {
                Swipes(this).swipeToRight(temporalArray)
                Log.d("swipes", "Arena: RIGHT")
            }
            Directions.LEFT -> {
                Swipes(this).swipeToLeft(temporalArray)
                Log.d("swipes", "Arena: LEFT")
            }
            Directions.UP -> {
                Swipes(this).swipeToUp(temporalArray)
                Log.d("swipes", "Arena: UP")
            }
            Directions.DOWN -> {
                Swipes(this).swipeToDown(temporalArray)
                Log.d("swipes", "Arena: DOWN")
            }
        }
        usualMove(temporalArray)
        return _game.value
    }

    private fun addNewDigit(array: MutableList<Int?>): MutableList<Int?>? {
        val tempArr = mutableListOf<Int>()
        repeat(array.size) {
            if (array[it] == null) {
                tempArr.add(it)
            }
        }
        if (tempArr.isNotEmpty()) {
            val pos = tempArr.random()
            array[pos] = selectRandomDigit()
            Log.d("DDDDDD", "New digit at $pos position")
        } else return null
        return array
    }

    fun newGame(): Game {
        val position = (0 until _game.value.matrix.array.size).random()
        val value = selectRandomDigit()
        val temporalArray = _game.value.matrix.array.map { it }.toMutableList()
        _game.value.matrix.array.replaceAll {
            null
        }
        temporalArray[position] = value
        _game.update { game ->
            game.copy(
                matrix = game.matrix.matrixCopy(temporalArray),
                score = 0,
                move = 0
            )
        }
        return _game.value
    }

    private fun selectRandomDigit(): Int {
        val a = (0..100).random()
        return if (a < 85) 2 else 4
    }
}