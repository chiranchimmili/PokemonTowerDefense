package com.my.pokemontowerdefense

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.my.pokemontowerdefense.ui.login.ConfigScreen
import kotlinx.android.synthetic.main.activity_config_screen.*
import android.annotation.SuppressLint
import android.graphics.Rect
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_game_screen.*


open class GameScreen() : AppCompatActivity() {

    var state = 0

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

            placement()

        }

        val layout1 = findViewById<RelativeLayout>(R.id.location1relative)
        val layout2 = findViewById<RelativeLayout>(R.id.location2relative)

    }

    fun placement() {

        val location1 = findViewById<Button>(R.id.location1button)
        val location2 = findViewById<Button>(R.id.location2button)
        location1.visibility = View.VISIBLE
        location2.visibility = View.VISIBLE


        location1.setOnClickListener{

            location1.visibility = View.INVISIBLE
            location2.visibility = View.INVISIBLE
            placeTower(location2relative)
        }

        location2.setOnClickListener{

            location1.visibility = View.INVISIBLE
            location2.visibility = View.INVISIBLE
            placeTower(location1relative)

        }
    }

    fun placeTower(view: ViewGroup) {

        val imageView = ImageView(this)
        imageView.layoutParams= LinearLayout.LayoutParams(400, 400)
        imageView.x= 20F
        imageView.y= 20F
        val imgResId = R.drawable.charmander
        var resId = imgResId
        imageView.setImageResource(resId)

        view?.addView(imageView)

    }
}
