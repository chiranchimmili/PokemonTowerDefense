package com.my.pokemontowerdefense

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.my.pokemontowerdefense.ui.login.ConfigScreen

class GameOverScreen : AppCompatActivity() {
    private fun hideSystemBars() {
        val windowInsetsController =
            ViewCompat.getWindowInsetsController(window.decorView) ?: return
        // Configure the behavior of the hidden system bars
        windowInsetsController.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        // Hide both the status bar and the navigation bar
        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        hideSystemBars()
        super.onCreate(savedInstanceState)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_game_over_screen)

        val restartButton = findViewById<Button>(R.id.restartButton)

        restartButton.setOnClickListener{
            val intent = Intent(this, ConfigScreen::class.java)
            startActivity(intent)
        }
    }
}