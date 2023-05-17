package com.example.game2048

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GameViewModel : ViewModel() {

    private val _matrix = MutableStateFlow(Matrix())
    val matrix: StateFlow<Matrix> = _matrix.asStateFlow()

    fun swipeToDown(): Matrix {
        val temporalArray = _matrix.value.array.map { it }.toMutableList()
        Swipes().swipeToDown(temporalArray)
        addNewDigit(temporalArray)
        _matrix.update {
            it.matrixCopy(temporalArray)
        }
        return _matrix.value
    }
    fun swipeToUp(): Matrix {
        val temporalArray = _matrix.value.array.map { it }.toMutableList()
        Swipes().swipeToUp(temporalArray)
        addNewDigit(temporalArray)
        _matrix.update {
            it.matrixCopy(temporalArray)
        }
        return _matrix.value
    }

    fun swipeToRight(): Matrix {
        val temporalArray = _matrix.value.array.map { it }.toMutableList()
        Swipes().swipeToRight(temporalArray)
        addNewDigit(temporalArray)
        _matrix.update {
            it.matrixCopy(temporalArray)
        }
        return _matrix.value
    }

    fun swipeToLeft(): Matrix {
        val temporalArray = _matrix.value.array.map { it }.toMutableList()
        Swipes().swipeToLeft(temporalArray)
        addNewDigit(temporalArray)
        _matrix.update {
            it.matrixCopy(temporalArray)
        }
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
            Log.d("DDDDDD", "new digit at $pos position")
        }
        else return null

        return array
    }
    fun newGame(): Matrix {
        _matrix.value.array.replaceAll {
            null
        }
        val position = (0..15).random()
        val value = selectRandomDigit()
        val temporalArray = _matrix.value.array.map { it }.toMutableList()
        temporalArray[position] = value
        _matrix.update {
            it.matrixCopy(temporalArray)
        }
        return _matrix.value
    }

    private fun selectRandomDigit(): Int {
        val a = (0..100).random()
        return if (a < 80) 2 else 4
    }
}