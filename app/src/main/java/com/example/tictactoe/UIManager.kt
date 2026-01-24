package com.example.tictactoe

import android.graphics.Color
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat

class UIManager(
    private val statusTextView: TextView,
    private val playAgainButton: Button,
    private val buttons: Array<Button>,
    private val context: MainActivity
) {
    
    fun updateStatus(player: String) {
        statusTextView.text = "Player $player Turn"
    }
    
    fun showWinMessage(player: String) {
        statusTextView.text = "Player $player Wins!"
    }
    
    fun showDrawMessage() {
        statusTextView.text = "It's a Draw!"
    }
    
    fun setCellText(index: Int, player: String) {
        buttons[index].text = player
        buttons[index].isEnabled = false
        
        val color = if (player == "X") {
            ContextCompat.getColor(context, R.color.player_x)
        } else {
            ContextCompat.getColor(context, R.color.player_o)
        }
        buttons[index].setTextColor(color)
    }
    
    fun highlightWinningLine(positions: List<Int>) {
        val highlightColor = ContextCompat.getColor(context, R.color.winning_highlight)
        positions.forEach { index ->
            buttons[index].setBackgroundColor(highlightColor)
        }
    }
    
    fun disableAllButtons() {
        buttons.forEach { it.isEnabled = false }
    }
    
    fun showPlayAgainButton() {
        playAgainButton.visibility = View.VISIBLE
    }
    
    fun hidePlayAgainButton() {
        playAgainButton.visibility = View.GONE
    }
    
    fun resetButtons() {
        buttons.forEach { button ->
            button.text = ""
            button.isEnabled = true
            button.setTextColor(Color.WHITE)
            button.background = ContextCompat.getDrawable(context, R.drawable.button_ripple)
        }
    }
}
