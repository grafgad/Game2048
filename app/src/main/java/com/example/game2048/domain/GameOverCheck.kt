package com.example.game2048.domain

import com.example.game2048.data.ROWCOUNT

class GameOverCheck {
    fun canContinue(array: MutableList<Int?>): Boolean {
        var isHorizontalSum = false
        var isVerticalSum = false
        val hasNulls = array.contains(null)
        if (!hasNulls) {
            // проверка по горизонталм
            for (line in 0 until array.size / ROWCOUNT) {
                val startIndex = line * ROWCOUNT // начало линии
                val endIndex = startIndex + ROWCOUNT - 1 // конец линии

                for (digit1 in startIndex until endIndex) {
                    val digit2 = digit1 + 1
                    if (array[digit1] == array[digit2]) {
                        isHorizontalSum = true
                    }
                }
            }
            // проверка по вертикали
            for (digit1 in 0 until array.size - ROWCOUNT) {
                val digit2 = digit1 + ROWCOUNT
                if (array[digit1] == array[digit2]) {
                    isVerticalSum = true
                }
            }
        }
        if (!isHorizontalSum && !isVerticalSum && !hasNulls) {
            return false // game over
        }
        return true // can continue
    }
}