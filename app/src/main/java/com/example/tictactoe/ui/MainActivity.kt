package com.example.tictactoe.ui

import android.content.Context
import android.media.AudioManager
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.tictactoe.R
import com.example.tictactoe.databinding.ActivityMainBinding
import com.example.tictactoe.domain.GameEngine
import com.example.tictactoe.model.GameResult
import com.example.tictactoe.model.Player

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val game = GameEngine()
    private val gridButtons = mutableListOf<Button>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupGrid()
        setupPlayAgainButton()
        updateUI()
    }

    private fun setupGrid() {
        gridButtons.addAll(
            listOf(
                binding.button0, binding.button1, binding.button2,
                binding.button3, binding.button4, binding.button5,
                binding.button6, binding.button7, binding.button8
            )
        )

        gridButtons.forEachIndexed { index, button ->
            button.setOnClickListener { onCellClicked(index) }
        }
    }

    private fun setupPlayAgainButton() {
        binding.playAgainButton.setOnClickListener {
            game.reset()
            resetGridUI()
            updateUI()
        }
    }

    private fun onCellClicked(position: Int) {
        if (!game.makeMove(position)) return

        playSoundEffect()
        updateUI()
    }

    private fun updateUI() {
        val state = game.currentState

        gridButtons.forEachIndexed { index, button ->
            val player = state.board[index]
            button.text = player?.symbol ?: ""
            button.isEnabled = player == null && !state.isGameOver
            
            player?.let { button.setTextColor(getPlayerColor(it)) }
        }

        when (val result = state.result) {
            is GameResult.Win -> {
                binding.statusTextView.text = getString(R.string.player_wins, result.winner.symbol)
                highlightWinningCells(result.winningPositions)
                binding.playAgainButton.visibility = View.VISIBLE
            }
            is GameResult.Draw -> {
                binding.statusTextView.text = getString(R.string.game_draw)
                binding.playAgainButton.visibility = View.VISIBLE
            }
            is GameResult.InProgress -> {
                binding.statusTextView.text = getString(R.string.player_turn, state.currentPlayer.symbol)
                binding.playAgainButton.visibility = View.GONE
            }
        }
    }

    private fun highlightWinningCells(positions: List<Int>) {
        val highlightColor = ContextCompat.getColor(this, R.color.winning_highlight)
        positions.forEach { gridButtons[it].setBackgroundColor(highlightColor) }
    }

    private fun resetGridUI() {
        gridButtons.forEach { button ->
            button.text = ""
            button.isEnabled = true
            button.setTextColor(ContextCompat.getColor(this, R.color.white))
            button.background = ContextCompat.getDrawable(this, R.drawable.button_ripple)
        }
        binding.playAgainButton.visibility = View.GONE
    }

    private fun getPlayerColor(player: Player) = ContextCompat.getColor(
        this,
        if (player == Player.X) R.color.player_x else R.color.player_o
    )

    private fun playSoundEffect() {
        (getSystemService(Context.AUDIO_SERVICE) as? AudioManager)?.playSoundEffect(
            AudioManager.FX_KEY_CLICK
        )
    }
}
