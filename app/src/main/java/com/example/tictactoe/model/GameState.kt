package com.example.tictactoe.model

data class GameState(
    val board: List<Player?> = List(9) { null },
    val currentPlayer: Player = Player.X,
    val result: GameResult = GameResult.InProgress
) {
    val isGameOver: Boolean
        get() = result !is GameResult.InProgress
}
