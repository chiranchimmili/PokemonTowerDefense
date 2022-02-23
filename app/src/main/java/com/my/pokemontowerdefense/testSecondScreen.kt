package com.my.pokemontowerdefense

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class testSecondScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_second_screen)
        var monumentHealth: Int;
        var startingMoney: Int;
        startingMoney = 40
        monumentHealth = 34
        val startingMoneyView: TextView = findViewById<TextView>(R.id.startingMoney)
        startingMoneyView.text = getString(startingMoney)

        val healthView: TextView = findViewById<TextView>(R.id.monumentHealth)
        healthView.text = getString(monumentHealth)
    }
}