package com.example.game2048

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GameViewModel : ViewModel() {

    private val _matrix = MutableStateFlow(Matrix())
    val matrix: StateFlow<Matrix> = _matrix.asStateFlow()

    fun onClick(): Matrix {
        val position = (0..15).random()
        val value = 5
        val arr = _matrix.value.array.map { it }.toMutableList()
        if (arr[position] == null) {
            arr[position] = value
        }
        _matrix.update {
            it.matrixCopy(arr)
        }
        return _matrix.value
    }

    fun newGame(): Matrix {
        _matrix.value.array.replaceAll {
            null }
        val position = (0..15).random()
        val value = selectRandomDigit()
        val arr = _matrix.value.array.map { it }.toMutableList()
        arr[position] = value
        _matrix.update {
            it.matrixCopy(arr)
        }
        return _matrix.value
    }

   private fun selectRandomDigit(): Int {
        val a = (0..100).random()
        return if (a<50) 2 else 4
    }
}