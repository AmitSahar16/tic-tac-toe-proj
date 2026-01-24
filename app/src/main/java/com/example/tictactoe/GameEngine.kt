package com.example.tictactoe

class GameEngine {
    
    private var board = Array(3) { Array(3) { "" } }
    private var currentPlayer = "X"
    private var moveCount = 0
    private var isGameOver = false
    
    fun makeMove(row: Int, col: Int): Boolean {
        if (isGameOver || board[row][col].isNotEmpty()) {
            return false
        }
        
        board[row][col] = currentPlayer
        moveCount++
        return true
    }
    
    fun checkWinner(): WinResult {
        // Check rows
        for (i in 0..2) {
            if (board[i][0].isNotEmpty() && 
                board[i][0] == board[i][1] && 
                board[i][1] == board[i][2]) {
                return WinResult.Win(board[i][0], listOf(i * 3, i * 3 + 1, i * 3 + 2))
            }
        }
        
        // Check columns
        for (i in 0..2) {
            if (board[0][i].isNotEmpty() && 
                board[0][i] == board[1][i] && 
                board[1][i] == board[2][i]) {
                return WinResult.Win(board[0][i], listOf(i, i + 3, i + 6))
            }
        }
        
        // Check diagonals
        if (board[0][0].isNotEmpty() && 
            board[0][0] == board[1][1] && 
            board[1][1] == board[2][2]) {
            return WinResult.Win(board[0][0], listOf(0, 4, 8))
        }
        
        if (board[0][2].isNotEmpty() && 
            board[0][2] == board[1][1] && 
            board[1][1] == board[2][0]) {
            return WinResult.Win(board[0][2], listOf(2, 4, 6))
        }
        
        // Check for draw
        if (moveCount == 9) {
            return WinResult.Draw
        }
        
        return WinResult.None
    }
    
    fun switchPlayer() {
        currentPlayer = if (currentPlayer == "X") "O" else "X"
    }
    
    fun getCurrentPlayer(): String = currentPlayer
    
    fun setGameOver(gameOver: Boolean) {
        isGameOver = gameOver
    }
    
    fun reset() {
        board = Array(3) { Array(3) { "" } }
        currentPlayer = "X"
        moveCount = 0
        isGameOver = false
    }
    
    sealed class WinResult {
        data class Win(val player: String, val positions: List<Int>) : WinResult()
        object Draw : WinResult()
        object None : WinResult()
    }
}
