package com.example.tictactoe

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    
    private lateinit var gameEngine: GameEngine
    private lateinit var uiManager: UIManager
    private lateinit var soundManager: SoundManager
    private lateinit var buttons: Array<Button>
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        initializeComponents()
        setupClickListeners()
    }
    
    private fun initializeComponents() {
        // Initialize game engine
        gameEngine = GameEngine()
        
        // Initialize UI elements
        val statusTextView = findViewById<TextView>(R.id.statusTextView)
        val playAgainButton = findViewById<Button>(R.id.playAgainButton)
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
        
        // Initialize managers
        uiManager = UIManager(statusTextView, playAgainButton, buttons, this)
        soundManager = SoundManager(this)
    }
    
    private fun setupClickListeners() {
        // Set click listeners for grid buttons
        buttons.forEachIndexed { index, button ->
            button.setOnClickListener { onCellClicked(index) }
        }
        
        // Set click listener for Play Again button
        findViewById<Button>(R.id.playAgainButton).setOnClickListener { resetGame() }
    }
    
    private fun onCellClicked(index: Int) {
        val row = index / 3
        val col = index % 3
        
        // Attempt to make move
        if (!gameEngine.makeMove(row, col)) {
            return
        }
        
        val currentPlayer = gameEngine.getCurrentPlayer()
        
        // Play sound and update UI
        soundManager.playMoveSound()
        uiManager.setCellText(index, currentPlayer)
        
        // Check game result
        when (val result = gameEngine.checkWinner()) {
            is GameEngine.WinResult.Win -> {
                handleWin(result.player, result.positions)
            }
            is GameEngine.WinResult.Draw -> {
                handleDraw()
            }
            is GameEngine.WinResult.None -> {
                gameEngine.switchPlayer()
                uiManager.updateStatus(gameEngine.getCurrentPlayer())
            }
        }
    }
    
    private fun handleWin(winner: String, positions: List<Int>) {
        uiManager.highlightWinningLine(positions)
        uiManager.showWinMessage(winner)
        uiManager.disableAllButtons()
        uiManager.showPlayAgainButton()
        gameEngine.setGameOver(true)
    }
    
    private fun handleDraw() {
        uiManager.showDrawMessage()
        uiManager.disableAllButtons()
        uiManager.showPlayAgainButton()
        gameEngine.setGameOver(true)
    }
    
    private fun resetGame() {
        gameEngine.reset()
        uiManager.resetButtons()
        uiManager.updateStatus(gameEngine.getCurrentPlayer())
        uiManager.hidePlayAgainButton()
    }
}
