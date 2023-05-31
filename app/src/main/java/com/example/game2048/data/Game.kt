package com.example.game2048.data

data class Game(
    var matrix: Matrix = Matrix(),
    var score: Int = 0,
    var move: Int = 0,
)
