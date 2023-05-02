package com.example.game2048

class Swipes {
    fun newGame() {

    }

    fun swipeToUp() {}
    private var line = mutableListOf(2, 2, null, 2)

    private val matrix = mutableListOf(
        null, 8192, 4096, 2048,
        null, null, 256, 128,
        8, 2, null, null,
        4, 2, 2, null
    )
    private val tempArr = mutableListOf<Int?>()
    private var summed = false
    // собрать элементы с цифрами в начале
    fun swipeToLeft(/*matrix: MutableList<Int?>*/): Unit {
        // разделили на линии
        for (line in 0 until matrix.size / ROWCOUNT) {
            val startIndex = line * ROWCOUNT // начало линии
            val endIndex = startIndex + ROWCOUNT - 1 // конец линии
            // проходим по элементам линии
            for (digit in startIndex..endIndex) {
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
            // проверяем длину временного массива и добавляем в его конец null,
            // если он короче длины исходного
            while (tempArr.size <= endIndex) {
                tempArr.add(null)
            }
        }



//        // поиск и суммирование первых одинаковых элементов
//        for (digit in line.indices) {
//            for (digit2 in digit + 1 until line.size) {
//                if (line[digit] == line[digit2]) {
//                    if (summed) {
//                        break
//                    }
//                    summed = true
//                    line[digit] = (line[digit]!!.times(2))
//                    line[digit2] = null
//                }
//            }
//        }
//        println(line) // печать измененного массива
//        // удаление всех null из массива
//        line.filterNotNullTo(tempArr)
//        // присвоение null всем элементам исходного массива
//        for (i in 0 until line.size) {
//            line[i] = null
//        }
//        // добавление новых элементов в старый массив
//        for (k in 0 until tempArr.size) {
//            line[k] = tempArr[k]
//        }
//        println(line)
    }

    fun swipeToDown() {

//        for (line in 3 downTo 0) {
//            var lowestEmptyTile: Int? = Fields.matrix.size - 1
//            var lowestFullTile: Int? = null
//            for (digit in 0 until 4) {
//                val tile = Fields.matrix[line][digit]
//                if (tile != null && line != 0) {
//                    lowestFullTile = Fields.matrix[line-1][digit]
//                    if (lowestFullTile != null) {
//                        Collections.swap(Fields.matrix[line], digit, lowestFullTile)
//                    }
//                }
//            }
//        }

//        for (line in 0 until 4) {
//            var lowestEmptyTile = Fields.matrix.size - 1
//            // считаем снизу вверх
//            for (digit in 3 downTo 0) {
//                val tile = Fields.matrix[digit][line]
//                if (tile != null) {
//                    Fields.matrix[lowestEmptyTile][line] = tile
//                    Fields.matrix[digit][line] = null
//                }
//                lowestEmptyTile = digit
//            }
//        }
    }

    fun swipeToRight() {
        //собрать элементы с цифрами в конце
        if (line.contains(null)) {
            for (element in line.size - 1 downTo 0) {
                if (line.last() == null) {
                    line.add(0, null)
                    line.removeAt(line.size - 1)
                    println(line)
                }
            }
        }
    }
}