package com.example.game2048

import androidx.lifecycle.ViewModel
import com.example.game2048.Fields.emptyMatrix
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GameViewModel : ViewModel() {
    private var _matrix = MutableStateFlow(Fields.emptyMatrix)
    val matrix: StateFlow<emptyMatrix> = _matrix.asStateFlow()

    fun onClick() {
        val a = 1//(0..3).random()
        val b = 1//(0..3).random()

        _matrix.value.smth[a][b] = 2

        _matrix.update {
            _matrix.value
        }
    }


}