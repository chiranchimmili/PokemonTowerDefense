package com.my.pokemontowerdefense

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.my.pokemontowerdefense.ui.login.ConfigScreen

class GameOverScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
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