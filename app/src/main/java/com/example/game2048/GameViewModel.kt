package com.example.game2048

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.*

const val ROWCOUNT = 4

class GameViewModel : ViewModel() {

    private val array2: MutableList<Int?> = mutableListOf(
        null, 8192, 4096, 2048,
        1024, null, 256, 128,
        8, 2, null, 64,
        4,2,2,null
    )

    private val _matrix = MutableStateFlow(Matrix())
    val matrix: StateFlow<Matrix> = _matrix.asStateFlow()

//    fun Matrix.clone(): Matrix {
//        val matr = Matrix()
//        matr.array = array
//        return matr
//    }

    fun onClick() {
        val position = 6//(0..3).random()
        val value = 5
        _matrix.update {
            array2[position] = value
            it.copy(
                array = array2
            )
        }
    }
}