package com.my.pokemontowerdefense

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.my.pokemontowerdefense.ui.login.ConfigScreen
import kotlinx.android.synthetic.main.activity_game_over_screen.*
import kotlinx.android.synthetic.main.activity_win_screen.*

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

        var stats = intent.extras?.get("STATS") as Stats

        var stat4: TextView? = findViewById(R.id.stat4)
        var stat5: TextView? = findViewById(R.id.stat5)
        var stat6: TextView? = findViewById(R.id.stat6)

        displayStats(stats)

        restartButton.setOnClickListener{
            val intent = Intent(this, WelcomeScreen::class.java)
            startActivity(intent)
            finish()
            System.exit(0)
        }
    }

    @SuppressLint("SetTextI18n")
    fun displayStats(stats : Stats) {
        stat6.text = "Damage Taken: " + (stats.damageTaken).toString()
        stat5.text = "Money Spent: " + (stats.moneySpent).toString()
        stat4.text = "Enemies Killed: " + (stats.enemiesKilled).toString()
    }
}