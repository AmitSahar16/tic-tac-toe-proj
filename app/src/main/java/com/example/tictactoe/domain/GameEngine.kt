package com.example.tictactoe.domain

import com.example.tictactoe.model.GameResult
import com.example.tictactoe.model.GameState
import com.example.tictactoe.model.Player

class GameEngine {
    private var state = GameState()

    val currentState: GameState
        get() = state

    fun makeMove(position: Int): Boolean {
        if (state.isGameOver || position !in 0..8 || state.board[position] != null) {
            return false
        }

        val newBoard = state.board.toMutableList().apply {
            this[position] = state.currentPlayer
        }

        val result = checkGameResult(newBoard)
        val nextPlayer = if (result is GameResult.InProgress) {
            state.currentPlayer.next()
        } else {
            state.currentPlayer
        }

        state = state.copy(
            board = newBoard,
            currentPlayer = nextPlayer,
            result = result
        )

        return true
    }

    fun reset() {
        state = GameState()
    }

    private fun checkGameResult(board: List<Player?>): GameResult {
        val winPatterns = listOf(
            listOf(0, 1, 2), listOf(3, 4, 5), listOf(6, 7, 8),
            listOf(0, 3, 6), listOf(1, 4, 7), listOf(2, 5, 8),
            listOf(0, 4, 8), listOf(2, 4, 6)
        )

        winPatterns.forEach { pattern ->
            val (a, b, c) = pattern
            val player = board[a]
            if (player != null && board[b] == player && board[c] == player) {
                return GameResult.Win(player, pattern)
            }
        }

        return if (board.all { it != null }) {
            GameResult.Draw
        } else {
            GameResult.InProgress
        }
    }
}
