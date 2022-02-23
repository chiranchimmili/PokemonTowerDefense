package com.my.pokemontowerdefense

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class GameScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_screen)

        var monumentHealth: Int;
        var startingMoney: Int;

        if (hardClicked) {
            monumentHealth = 50;
            startingMoney = 500;
        }
        else if (mediumClicked) {
            monumentHealth = 100;
            startingMoney = 1000;
        }
        else {
            monumentHealth = 200;
            startingMoney = 2000;
        }

        val startingMoneyView: TextView = findViewById<TextView>(R.id.startingMoney)
        startingMoneyView.text = getString(startingMoney)

        val healthView: TextView = findViewById<TextView>(R.id.monumentHealth)
        healthView.text = getString(monumentHealth)
    }
}