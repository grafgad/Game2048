package com.example.game2048

object Fields {

     var line1: MutableList<Int?> = mutableListOf(null, 8192, 4096, 2048)
    private var line2: MutableList<Int?> = mutableListOf(1024, null, 256, 128)
    private var line3: MutableList<Int?> = mutableListOf(8, 2, null, 64)
    private var line4: MutableList<Int?> = mutableListOf(4,2,2,null)





    var matrix = arrayOf(line1, line2, line3, line4)
}
object emptyMatrix {

    private val line: MutableList<Int?> = mutableListOf(null, null, null, 1)

    val newMatrix: MutableList<MutableList<Int?>> = mutableListOf(line, line, line, line)

}