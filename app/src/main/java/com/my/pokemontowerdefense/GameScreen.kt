package com.my.pokemontowerdefense

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.my.pokemontowerdefense.ui.login.ConfigScreen
import kotlinx.android.synthetic.main.activity_config_screen.*


open class GameScreen() : AppCompatActivity() {

    var player1 = Player()

    var difficulty: String = ""
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
            player1.money = 500
            difficulty = "hard"
        }
        else if (med == "true") {
            monumentHealth = 100;
            player1.money = 1000
            difficulty = "medium"
        }
        else {
            monumentHealth = 200;
            player1.money = 2000
            difficulty = "easy"
        }

        val buyTower1 = findViewById<Button>(R.id.BuyTower1)
        val buyTower2 = findViewById<Button>(R.id.BuyTower2)
        val buyTower3 = findViewById<Button>(R.id.BuyTower3)


        var moneyView: TextView = findViewById<TextView>(R.id.startingMoney)
        moneyView.text = player1.money.toString()

        var healthView: TextView = findViewById<TextView>(R.id.monumentHealth)
        healthView.text = monumentHealth.toString()

        buyTower1.setOnClickListener{
            var shop = Shop()
            var tower1 = SubTower1()

            tower1.cost = 400
            player1.money -= tower1.cost
            moneyView.text = player1.money.toString()

        }



    }
}