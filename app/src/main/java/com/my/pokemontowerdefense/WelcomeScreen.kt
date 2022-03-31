package com.my.pokemontowerdefense

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.content.Intent
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.my.pokemontowerdefense.ui.login.ConfigScreen

class WelcomeScreen : AppCompatActivity() {
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
        setContentView(R.layout.activity_welcome_screen)

        val startButton = findViewById<Button>(R.id.start_button)

        val quitButton = findViewById<Button>(R.id.quit_button)

        startButton.setOnClickListener{
            val intent = Intent(this, ConfigScreen::class.java)
            startActivity(intent)
            System.exit(0)
        }

        quitButton.setOnClickListener{
            finish()
            System.exit(0)
        }
    }
    
}