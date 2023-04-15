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
        arr[position] = value

        _matrix.update {
            it.copy(
                array = arr
            )
        }
        return _matrix.value
    }
}