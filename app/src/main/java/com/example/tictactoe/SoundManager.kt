package com.example.tictactoe

import android.content.Context
import android.media.AudioManager

class SoundManager(private val context: Context) {
    
    fun playMoveSound() {
        try {
            val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
            audioManager.playSoundEffect(AudioManager.FX_KEY_CLICK)
        } catch (e: Exception) {
            // Ignore if sound fails
        }
    }
}
