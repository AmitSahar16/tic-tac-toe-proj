package com.example.tictactoe.model

sealed class GameResult {
    data class Win(val winner: Player, val winningPositions: List<Int>) : GameResult()
    data object Draw : GameResult()
    data object InProgress : GameResult()
}
