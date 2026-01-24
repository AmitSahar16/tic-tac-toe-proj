package com.example.tictactoe

import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    // Game state variables
    private var board = Array(3) { Array(3) { "" } }
    private var currentPlayer = "X"
    private var moveCount = 0
    private var isGameOver = false
    private var winningPositions: List<Int>? = null

    // UI elements
    private lateinit var statusTextView: TextView
    private lateinit var playAgainButton: Button
    private lateinit var buttons: Array<Button>

    // Sound
    private var moveSound: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize UI elements
        statusTextView = findViewById(R.id.statusTextView)
        playAgainButton = findViewById(R.id.playAgainButton)
        buttons = arrayOf(
            findViewById(R.id.button0),
            findViewById(R.id.button1),
            findViewById(R.id.button2),
            findViewById(R.id.button3),
            findViewById(R.id.button4),
            findViewById(R.id.button5),
            findViewById(R.id.button6),
            findViewById(R.id.button7),
            findViewById(R.id.button8)
        )

        // Set click listeners for all buttons
        buttons.forEachIndexed { index, button ->
            button.setOnClickListener { onCellClicked(index) }
        }

        // Set click listener for Play Again button
        playAgainButton.setOnClickListener { resetGame() }

        // Initialize sound (using system sound as placeholder)
        initializeSound()
    }

    private fun initializeSound() {
        // Use a system sound for button clicks
        // In a real app, you would add custom sound files to res/raw/
    }

    override fun onDestroy() {
        supPlay sound effect
        playMoveSound()

        // Set button text to current player and disable it
        buttons[index].text = currentPlayer
        buttons[index].isEnabled = false

        // Set text color based on player
        val color = if (currentPlayer == "X") {
            ContextCompat.getColor(this, R.color.player_x)
        } else {
            ContextCompat.getColor(this, R.color.player_o)
        }
        buttons[index].setTextColor(color)

        // Update board state
        board[row][col] = currentPlayer
        moveCount++

        // Check for win or draw
        val winPositions = checkWinner()
        if (winPositions != null) {
            winningPositions = winPositions
            highlightWinningLine(winPositions) over or cell already used or button disabled
            if (isGameOver || board[row][col].isNotEmpty() || !buttons[index].isEnabled) {
                return
            }

            // Set button text to current player and disable it
            buttons[index].text = currentPlayer
            buttons[index].isEnabled = false

            // Update board state
            board[row][col] = currentPlayer
            moveCount++

            // Check for win or draw
            if (checkWinner()) {
                statusTextView.text = "Player $currentPlayer Wins!"
                isGameOver = true
                disableAllButtons()
                playAgainButton.visibility = View.VISIBLE
                return
            }
            playMoveSound() {
                try {
                    // Play default click sound
                    val audioManager = getSystemService(AUDIO_SERVICE) as android.media.AudioManager
                    audioManager.playSoundEffect(android.media.AudioManager.FX_KEY_CLICK)
                } catch (e: Exception) {
                    // Ignore if sound fails
                }
            }

            private fun checkWinner(): List<Int>?
            if (moveCount == 9) {
                statusTextView.text = "It's a Draw!"
                isGameOver = true
                disableAllButtons()List<Int>? {
                    // Check rows
                    for (i in 0..2) {
                        if (board[i][0].isNotEmpty() &&
                            board[i][0] == board[i][1] &&
                            board[i][1] == board[i][2]) {
                            return listOf(i * 3, i * 3 + 1, i * 3 + 2)
                        }
                    }

                    // Check columns
                    for (i in 0..2) {
                        if (board[0][i].isNotEmpty() &&
                            board[0][i] == board[1][i] &&
                            board[1][i] == board[2][i]) {
                            return listOf(i, i + 3, i + 6)
                        }
                    }

                    // Check diagonals
                    if (board[0][0].isNotEmpty() &&
                        board[0][0] == board[1][1] &&
                        board[1][1] == board[2][2]) {
                        return listOf(0, 4, 8)
                    }

                    if (board[0][2].isNotEmpty() &&
                        board[0][2] == board[1][1] &&
                        board[1][1] == board[2][0]) {
                        return listOf(2, 4, 6)
                    }

                    return null
                }

                private fun highlightWinningLine(positions: List<Int>) {
                    val highlightColor = ContextCompat.getColor(this, R.color.winning_highlight)
                    positions.forEach { index ->
                        buttons[index].setBackgroundColor(highlightColor)
                    }[1] == board[2][2]) {
                        return true
                    }

                    if (board[0][2].isNotEmpt, colors, backgrounds and enable them
                    buttons.forEach { button ->
                        button.text = ""
                        button.isEnabled = true
                        button.setTextColor(Color.WHITE)
                        button.background = ContextCompat.getDrawable(this, R.drawable.button_ripple)
                    }

                    // Reset game state
                    moveCount = 0
                    currentPlayer = "X"
                    isGameOver = false
                    winningPositions = nullButtons() {
                        buttons.forEach { it.isEnabled = false }
                    }

                    private fun resetGame() {
                        // Clear board array
                        board = Array(3) { Array(3) { "" } }

                        // Reset all button texts and enable them
                        buttons.forEach { button ->
                            button.text = ""
                            button.isEnabled = true
                        }

                        // Reset game state
                        moveCount = 0
                        currentPlayer = "X"
                        isGameOver = false

                        // Update status TextView
                        statusTextView.text = "Player X Turn"

                        // Hide Play Again button
                        playAgainButton.visibility = View.GONE
                    }
                }