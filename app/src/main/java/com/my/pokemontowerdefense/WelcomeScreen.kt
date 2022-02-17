package com.my.pokemontowerdefense

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.content.Intent
import androidx.constraintlayout.widget.ConstraintLayout

class WelcomeScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome_screen)


        val startButton = findViewById<Button>(R.id.start_button)

        val quitButton = findViewById<Button>(R.id.quit_button)

        startButton.setOnClickListener{
            val intent = Intent(this, testSecondScreen::class.java)
            startActivity(intent)
        }

        quitButton.setOnClickListener{
            finish()
            System.exit(0)
        }
    }
    
}