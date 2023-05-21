package com.example.game2048

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GameViewModel : ViewModel() {

    private var _matrix = MutableStateFlow(Matrix()) // игровое поле с плитками
    val matrix: StateFlow<Matrix> = _matrix.asStateFlow() // передача игрового поля в compose

    private val _movesCount = MutableStateFlow(0) // подсчет хода
    val movesCount: StateFlow<Int> = _movesCount.asStateFlow() // передача кол-ва ходов в compose

    private val lastMoveTilePosotions = MutableStateFlow(Matrix()) // предыдущий ход для возврата
    private var canUseUndo: Boolean = true

    private var _gameScore = MutableStateFlow(0) // очки за ход во вьюмодели
    val gameScore: StateFlow<Int> = _gameScore.asStateFlow() // передача очков в compose

    private var moveScore = 0 // число очков за ход
    private var previousMoveScore = 0 //число очков для отмены хода


    fun setMoveScore(a: Int) {
        moveScore = a
        previousMoveScore = a
    }

    private fun updateScores() {
        _gameScore.update {
            _gameScore.value.plus(moveScore)
        }
        moveScore = 0
    }

    private fun usualMove(array: MutableList<Int?>) {
        lastMoveTilePosotions.value = _matrix.value
        updateScores()
        addNewDigit(array)
        _matrix.update {
            it.matrixCopy(array)
        }
        _movesCount.value++
        canUseUndo = true
        Log.d("moves", "newScore = $moveScore")
        Log.d("moves", "_gameScore = ${_gameScore.value}")
    }

    fun undoMove() {
        if (canUseUndo) {
            _matrix.value = lastMoveTilePosotions.value
            _movesCount.value--
            _gameScore.value -= previousMoveScore
            canUseUndo = false
        }
    }

    fun swipeToDown(): Matrix {
        val temporalArray = _matrix.value.array.map { it }.toMutableList()
        Swipes(this).swipeToDown(temporalArray)
        usualMove(temporalArray)
        return _matrix.value
    }

    fun swipeToUp(): Matrix {
        val temporalArray = _matrix.value.array.map { it }.toMutableList()
        Swipes(this).swipeToUp(temporalArray)
        usualMove(temporalArray)
        return _matrix.value
    }

    fun swipeToRight(): Matrix {
        val temporalArray = _matrix.value.array.map { it }.toMutableList()
        Swipes(this).swipeToRight(temporalArray)
        usualMove(temporalArray)
        return _matrix.value
    }

    fun swipeToLeft(): Matrix {
        val temporalArray = _matrix.value.array.map { it }.toMutableList()
        Swipes(this).swipeToLeft(temporalArray)
        usualMove(temporalArray)
        return _matrix.value
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

    fun newGame(): Matrix {
        _matrix.value.array.replaceAll {
            null
        }
        val position = (0 until _matrix.value.array.size).random()
        val value = selectRandomDigit()
        val temporalArray = _matrix.value.array.map { it }.toMutableList()
        temporalArray[position] = value
        _matrix.update {
            it.matrixCopy(temporalArray)
        }
        _gameScore.value = 0
        moveScore = 0
        _movesCount.value = 0
        return _matrix.value
    }

    private fun selectRandomDigit(): Int {
        val a = (0..100).random()
        return if (a < 85) 2 else 4
    }
}