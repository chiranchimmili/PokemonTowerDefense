package com.my.pokemontowerdefense

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Notification
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.*
import kotlinx.android.synthetic.main.activity_game_screen.*


open class GameScreen() : AppCompatActivity() {

    var player1 = Player()

    var difficulty: String = ""
        get() {
            return field
        }
        set(value) {
            field = value
        }

    var location1_on = false
    var location2_on = false
    var location3_on = false
    var location4_on = false
    var location5_on = false
    var location6_on = false
    var location7_on = false
    var location8_on = false
    var location9_on = false

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        var shop = Shop()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_screen)

        var monumentHealth: Int
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

        val CharmanderTower = CharmanderTower(difficulty);
        val BulbasaurTower = BulbasaurTower(difficulty);
        val SquirtleTower = SquirtleTower(difficulty);

        val buyTower1 = findViewById<ImageButton>(R.id.buyTower1Image)
        val buyTower2 = findViewById<ImageButton>(R.id.buyTower2Image)
        val buyTower3 = findViewById<ImageButton>(R.id.buyTower3Image)

        // Displays starting money, depending on difficulty
        var moneyView: TextView = findViewById<TextView>(R.id.startingMoney)
        moneyView.text = player1.money.toString()

        // Displays monument health, depending on difficulty
        var healthView: TextView = findViewById<TextView>(R.id.monumentHealth)
        healthView.text = monumentHealth.toString()

        buyTower1.setOnClickListener {
            if (shop.buyTower(CharmanderTower, player1)) {
                moneyView.text = player1.money.toString()
                placement(CharmanderTower.imgResId)
            } else {
                insufficientFunds()
            }
        }
        buyTower2.setOnClickListener {
            if (shop.buyTower(SquirtleTower, player1)) {
                moneyView.text = player1.money.toString()
                placement(SquirtleTower.imgResId)
            } else {
                insufficientFunds()
            }
        }
        buyTower3.setOnClickListener {
            if (shop.buyTower(BulbasaurTower, player1)) {
                moneyView.text = player1.money.toString()
                placement(BulbasaurTower.imgResId)
            } else {
                insufficientFunds()
            }
        }

    }

    // placement of towers functionality, can currently place in one of nine spots on screen
    fun placement(imgResId: Int) {
        val location1 = findViewById<Button>(R.id.location1button)
        val location2 = findViewById<Button>(R.id.location2button)
        val location3 = findViewById<Button>(R.id.location3button)
        val location4 = findViewById<Button>(R.id.location4button)
        val location5 = findViewById<Button>(R.id.location5button)
        val location6 = findViewById<Button>(R.id.location6button)
        val location7 = findViewById<Button>(R.id.location7button)
        val location8 = findViewById<Button>(R.id.location8button)
        val location9 = findViewById<Button>(R.id.location9button)

        val list = arrayListOf(location1, location2, location3, location4, location5, location6,
            location7, location8, location9)

        if (!(location1_on)) {
            location1.visibility = View.VISIBLE
        }
        if (!(location2_on)) {
            location2.visibility = View.VISIBLE
        }
        if (!(location3_on)) {
            location3.visibility = View.VISIBLE
        }
        if (!(location4_on)) {
            location4.visibility = View.VISIBLE
        }
        if (!(location5_on)) {
            location5.visibility = View.VISIBLE
        }
        if (!(location6_on)) {
            location6.visibility = View.VISIBLE
        }
        if (!(location7_on)) {
            location7.visibility = View.VISIBLE
        }
        if (!(location8_on)) {
            location8.visibility = View.VISIBLE
        }
        if (!(location9_on)) {
            location9.visibility = View.VISIBLE
        }

        location1.setOnClickListener {
            visibilityOff(list)
            location1_on = true
            placeTower(location1relative, imgResId)
        }
        location2.setOnClickListener {
            visibilityOff(list)
            location2_on = true
            placeTower(location2relative, imgResId)
        }
        location3.setOnClickListener {
            visibilityOff(list)
            location3_on = true
            placeTower(location3relative, imgResId)
        }
        location4.setOnClickListener {
            visibilityOff(list)
            location4_on = true
            placeTower(location4relative, imgResId)
        }
        location5.setOnClickListener {
            visibilityOff(list)
            location5_on = true
            placeTower(location5relative, imgResId)
        }
        location6.setOnClickListener {
            visibilityOff(list)
            location6_on = true
            placeTower(location6relative, imgResId)
        }
        location7.setOnClickListener {
            visibilityOff(list)
            location7_on = true
            placeTower(location7relative, imgResId)
        }
        location8.setOnClickListener {
            visibilityOff(list)
            location8_on = true
            placeTower(location8relative, imgResId)
        }
        location9.setOnClickListener {
            visibilityOff(list)
            location9_on = true
            placeTower(location9relative, imgResId)
        }
    }

    // Currently only places an imageview (charmander), need to make it so it places
    // a tower class or subclass object, which should also be an imageview
    fun placeTower(view: ViewGroup, resId: Int) {
        val imageButton = ImageButton(this)
        imageButton.layoutParams= LinearLayout.LayoutParams(400, 400)
        imageButton.x= 20F
        imageButton.y= 20F
        imageButton.setBackgroundColor(Color.TRANSPARENT)
        imageButton.setImageResource(resId)
        imageButton.scaleType = ImageView.ScaleType.FIT_START
        view?.addView(imageButton)
    }

    // turns off visibility of all tower placement buttons
    private fun visibilityOff(buttons: ArrayList<Button>) {
        for (b in buttons) {
            b.visibility = View.INVISIBLE
        }
    }

    private fun insufficientFunds() {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setPositiveButton("Insufficient Funds") { dialog, _ ->
            dialog.cancel()
        }
        val alert = dialogBuilder.create()
        alert.show()
    }
}
