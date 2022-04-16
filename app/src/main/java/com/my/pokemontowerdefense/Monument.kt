package com.my.pokemontowerdefense

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import com.my.pokemontowerdefense.ui.login.ConfigScreen
import java.io.Serializable


class Monument(textView: TextView?, difficulty: String) : Serializable{
    var health: Int = 0
    var initHealth: Int = 0
    var healthView: TextView? = textView;
    var gameIsOver: Boolean = false;

    init {
        if (difficulty == "hard") {
            health = 50
            initHealth = 50
        } else if (difficulty == "medium") {
            health = 100
            initHealth = 100
        } else {
            health = 200
            initHealth = 200
        }
        if (healthView != null) {
            healthView!!.text = health.toString()
        }
    }

    fun reduceMonumentHealth(context: Context?, damage : Int, stats: Stats) {
        health -= damage
        stats.damageTaken += damage
        if (health <= 0 && !gameIsOver) {
            gameIsOver = true
            if (context != null) {
                val intent = Intent(context, GameOverScreen::class.java)
                context.startActivity(intent)
                System.exit(0)
            }
        }
        if (healthView != null) {
            healthView!!.text = health.toString()
        }
    }

    fun monumentInitHealth(stats: Stats) {
        stats.monumentInitHealth = initHealth
    }

}