package com.example.tictactoe.model

enum class Player(val symbol: String) {
    X("X"),
    O("O");

    fun next() = if (this == X) O else X
}
