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

    var difficulty: String = ""
        get() {
            return field
        }
        set(value) {
            field = value
        }

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        var shop = Shop()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_screen)

        val intent = intent
        val med: String = intent.getStringExtra("mediumbutton").toString()
        val hard: String = intent.getStringExtra("hardbutton").toString()

        if (hard == "true") {
            difficulty = "hard"
        } else if (med == "true") {
            difficulty = "medium"
        } else {
            difficulty = "easy"
        }

        val CharmanderTower = CharmanderTower(difficulty);
        val BulbasaurTower = BulbasaurTower(difficulty);
        val SquirtleTower = SquirtleTower(difficulty);
        val monument = Monument(difficulty);
        val player = Player(difficulty)

        val buyTower1 = findViewById<ImageButton>(R.id.buyTower1Image)
        val buyTower2 = findViewById<ImageButton>(R.id.buyTower2Image)
        val buyTower3 = findViewById<ImageButton>(R.id.buyTower3Image)

        val location1 = Location(findViewById<Button>(R.id.location1button), location1relative)
        val location2 = Location(findViewById<Button>(R.id.location2button), location2relative)
        val location3 = Location(findViewById<Button>(R.id.location3button), location3relative)
        val location4 = Location(findViewById<Button>(R.id.location4button), location4relative)
        val location5 = Location(findViewById<Button>(R.id.location5button), location5relative)
        val location6 = Location(findViewById<Button>(R.id.location6button), location6relative)
        val location7 = Location(findViewById<Button>(R.id.location7button), location7relative)
        val location8 = Location(findViewById<Button>(R.id.location8button), location8relative)
        val location9 = Location(findViewById<Button>(R.id.location9button), location9relative)

        val locations = arrayListOf(
            location1, location2, location3, location4, location5, location6,
            location7, location8, location9
        )

        // Displays starting money, depending on difficulty
        var moneyView: TextView = findViewById<TextView>(R.id.startingMoney)
        moneyView.text = player.money.toString()

        // Displays monument health, depending on difficulty
        var healthView: TextView = findViewById<TextView>(R.id.monumentHealth)
        healthView.text = monument.health.toString()

        buyTower1.setOnClickListener {
            if (shop.buyTower(CharmanderTower, player)) {
                moneyView.text = player.money.toString()
                placement(CharmanderTower.imgResId, locations)
            } else {
                insufficientFunds()
            }
        }
        buyTower2.setOnClickListener {
            if (shop.buyTower(SquirtleTower, player)) {
                moneyView.text = player.money.toString()
                placement(SquirtleTower.imgResId, locations)
            } else {
                insufficientFunds()
            }
        }
        buyTower3.setOnClickListener {
            if (shop.buyTower(BulbasaurTower, player)) {
                moneyView.text = player.money.toString()
                placement(BulbasaurTower.imgResId, locations)
            } else {
                insufficientFunds()
            }
        }

    }

    // placement of towers functionality, can currently place in one of nine spots on screen
    fun placement(imgResId: Int, locations: ArrayList<Location>) : Boolean {
        for (location in locations) {
            if (!(location.hasTower)) {
                location.button.visibility = View.VISIBLE
            }
        }
        for (location in locations) {
            location.button.setOnClickListener {
                location.visibilityOff(locations)
                location.hasTower = true
                placeTower(location.layout, imgResId)
            }
        }
        return true
    }

    fun placeTower(view: ViewGroup, resId: Int) {
        val imageButton = ImageButton(this)
        imageButton.layoutParams = LinearLayout.LayoutParams(800, 800)
        imageButton.x = 20F
        imageButton.y = 20F
        imageButton.setBackgroundColor(Color.TRANSPARENT)
        imageButton.setImageResource(resId)
        //imageButton.scaleType = ImageView.ScaleType.FIT_START
        view?.addView(imageButton)
    }

    fun insufficientFunds() {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setPositiveButton("Insufficient Funds") { dialog, _ ->
            dialog.cancel()
        }
        val alert = dialogBuilder.create()
        alert.show()
    }
}


