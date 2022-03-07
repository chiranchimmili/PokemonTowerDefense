package com.my.pokemontowerdefense

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.ImageView
import com.my.pokemontowerdefense.ui.login.ConfigScreen
import kotlinx.android.synthetic.main.activity_config_screen.*
import android.annotation.SuppressLint
import android.graphics.Rect
import android.view.MotionEvent
import android.view.View.OnTouchListener
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_game_screen.*


open class GameScreen() : AppCompatActivity() {

    private lateinit var movingLayout: ViewGroup
    private lateinit var testImage: ImageView

    private var xDelta = 5
    private var yDelta = 5

    var player1 = Player()
    var difficulty: String = ""
        get() {
            return field
        }
        set(value) {
            field = value
        }

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_screen)

        movingLayout = findViewById(R.id.moving)
        testImage = findViewById(R.id.testBoi)

        testImage.setOnTouchListener(onTouchListener())

        var monumentHealth: Int = 0

        val intent = intent
        val med: String = intent.getStringExtra("mediumbutton").toString()
        val hard: String = intent.getStringExtra("hardbutton").toString()

        if (hard == "true") {
            monumentHealth = 50;
            player1.money = 500
            difficulty = "hard"
        } else if (med == "true") {
            monumentHealth = 100;
            player1.money = 1000
            difficulty = "medium"
        } else {
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

        buyTower1.setOnClickListener {
            var shop = Shop()
            var tower1 = SubTower1()

            tower1.cost = 400
            player1.money -= tower1.cost
            moneyView.text = player1.money.toString()

        }
    }
    @SuppressLint("ClickableViewAccessibility")
    fun onTouchListener(): OnTouchListener {
        return OnTouchListener { view, event ->
            val x = event.rawX.toInt()
            val y = event.rawY.toInt()

            // detecting user actions on moving

            when (event.action and MotionEvent.ACTION_MASK) {
                MotionEvent.ACTION_DOWN -> {
                    val lParams = view.layoutParams as RelativeLayout.LayoutParams
                    xDelta = x - lParams.leftMargin
                    yDelta = y - lParams.topMargin

                }
                MotionEvent.ACTION_UP -> {

                    Toast.makeText(
                        this,
                        "Tower Moved Successfully!", Toast.LENGTH_SHORT
                    )
                        .show()
                }
                MotionEvent.ACTION_MOVE -> {
                    // based on x and y coordinates (when moving image)
                    // and image is placed with it.
                    val layoutParams = view.layoutParams as RelativeLayout.LayoutParams
                    layoutParams.leftMargin = x - xDelta
                    layoutParams.topMargin = y - yDelta
                    layoutParams.rightMargin = 0
                    layoutParams.bottomMargin = 0
                    view.layoutParams = layoutParams
                }
            }

            // reflect the changes on screen
            movingLayout.invalidate()
            true
        }
    }
}