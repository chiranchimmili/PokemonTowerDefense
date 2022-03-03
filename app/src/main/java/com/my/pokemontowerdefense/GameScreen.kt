package com.my.pokemontowerdefense

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.my.pokemontowerdefense.ui.login.ConfigScreen
import kotlinx.android.synthetic.main.activity_config_screen.*


class GameScreen() : AppCompatActivity() {

    var difficulty: String = ""
        get() {
            return field
        }
        set(value) {
            field = value
        }

    var startingMoney: Int = 0
        get() {
            return field
        }
        set(value) {
            field = value
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_screen)

        var monumentHealth: Int = 0



        val intent = intent
        val med: String = intent.getStringExtra("mediumbutton").toString()
        val hard: String = intent.getStringExtra("hardbutton").toString()

        if (hard =="true") {
            monumentHealth = 50;
            startingMoney = 500;
            difficulty = "hard"
        }
        else if (med == "true") {
            monumentHealth = 100;
            startingMoney = 1000;
            difficulty = "medium"
        }
        else {
            monumentHealth = 200;
            startingMoney = 2000;
            difficulty = "easy"
        }


        var moneyView: TextView = findViewById<TextView>(R.id.startingMoney)
        moneyView.text = startingMoney.toString()

        var healthView: TextView = findViewById<TextView>(R.id.monumentHealth)
        healthView.text = monumentHealth.toString()
    }
}