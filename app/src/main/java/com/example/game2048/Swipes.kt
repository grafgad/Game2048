package com.example.game2048

class Swipes {
    fun newGame() {

    }

    fun swipeToUp() {

    }

    
    // собрать элементы с цифрами в конце
    fun swipeToRight(matrix: MutableList<Int?>): MutableList<Int?> {
        matrix.reverse() // переворачиваем список и работаем кок со свайпом влево
        val tempArr = mutableListOf<Int?>()

        for (line in 0 until matrix.size / ROWCOUNT) { // разделили на линии
            val startIndex = line * ROWCOUNT // начало линии
            val endIndex = startIndex + ROWCOUNT - 1 // конец линии

            for (digit in startIndex..endIndex) { // проходим по элементам линии
                var summed = false
                for (digit2 in digit + 1 until endIndex) {
                    // сравнение соседних элементов линии
                    if (matrix[digit] == matrix[digit2] && matrix[digit] != null) {
                        if (summed) {
                            break // усли суммирование уже произошло, то прерываем цикл
                        }
                        summed = true
                        matrix[digit] = matrix[digit]!!.times(2) // умножаем значение вдвое
                        matrix[digit2] = null // обнуляем соседнюю ячейку
                    }
                }
                // добавляем во временный массив числовые элементы
                if (matrix[digit] != null) {
                    tempArr.add(matrix[digit])
                }
            }
            // проверяем длину временного массива и добавляем в его КОНЕЦ null,
            // если он короче длины исходного
            while (tempArr.size <= endIndex) {
                tempArr.add(null)
            }
        }
        //записываем в старый массив новые значения
        repeat(times = tempArr.size) { position ->
            matrix[position] = tempArr[position]
        }
        matrix.reverse() // еще раз переворачиваем список обратно
        return matrix

//        val tempArr = mutableListOf<Int?>(
//        )
//
//        for (line in 0 until matrix.size / ROWCOUNT) { // разделили на линии
//            val startIndex = line * ROWCOUNT // начало линии
//            val endIndex = startIndex + ROWCOUNT - 1 // конец линии
//            for (digit in endIndex downTo startIndex) {
//                var summed = false
//                for (digit2 in digit - 1 downTo startIndex) {
//                    // сравнение соседних элементов линии
//                    if (matrix[digit] == matrix[digit2] && matrix[digit] != null) {
//                        if (summed) {
//                            break // если суммирование уже произошло, то прерываем цикл
//                        }
//                        summed = true
//                        matrix[digit] =
//                            matrix[digit]!!.times(2) // умножаем значение вдвое
//                        matrix[digit2] = null // обнуляем соседнюю ячейку
//                    }
//                }
//                // добавляем во временный массив числовые элементы
//                if (matrix[digit] != null) {
//                    tempArr.add(matrix[digit])
//                }
//            }
//            // проверяем длину временного массива и добавляем в его НАЧАЛО null,
//            // если он короче длины исходного
//            while (tempArr.size <= endIndex) {
//          // ТУТ ВСЕ ЗАВИСАЕТ, ЕСЛИ СПИСОК С КОНЦА ОБРАБАТЫВАТЬ :(((
//                tempArr.add(null)
//
//            }
//        }
//        //записываем в старый массив новые значения
//        repeat(times = tempArr.size) { position ->
//            matrix[position] = tempArr[position]
//        }
//        return matrix
    }


    // собрать элементы с цифрами в начале
    fun swipeToLeft(matrix: MutableList<Int?>): MutableList<Int?> {
        val tempArr = mutableListOf<Int?>()

        for (line in 0 until matrix.size / ROWCOUNT) { // разделили на линии
            val startIndex = line * ROWCOUNT // начало линии
            val endIndex = startIndex + ROWCOUNT - 1 // конец линии

            for (digit in startIndex..endIndex) { // проходим по элементам линии
                var summed = false
                for (digit2 in digit + 1 until endIndex) {
                    // сравнение соседних элементов линии
                    if (matrix[digit] == matrix[digit2] && matrix[digit] != null) {
                        if (summed) {
                            break // усли суммирование уже произошло, то прерываем цикл
                        }
                        summed = true
                        matrix[digit] = matrix[digit]!!.times(2) // умножаем значение вдвое
                        matrix[digit2] = null // обнуляем соседнюю ячейку
                    }
                }
                // добавляем во временный массив числовые элементы
                if (matrix[digit] != null) {
                    tempArr.add(matrix[digit])
                }
            }
            // проверяем длину временного массива и добавляем в его КОНЕЦ null,
            // если он короче длины исходного
            while (tempArr.size <= endIndex) {
                tempArr.add(null)
            }
        }
        //записываем в старый массив новые значения
        repeat(times = tempArr.size) { position ->
            matrix[position] = tempArr[position]
        }
        return matrix
    }

    fun swipeToDown() {
    }
}