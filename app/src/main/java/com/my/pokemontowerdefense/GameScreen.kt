package com.my.pokemontowerdefense

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_game_screen.*


open class GameScreen() : AppCompatActivity() {

    var difficulty: String = ""
        get() {
            return field
        }
        set(value) {
            field = value
        }

    private lateinit var player: Player;
    private lateinit var CharmanderTower: CharmanderTower;
    private lateinit var BulbasaurTower: BulbasaurTower;
    private lateinit var SquirtleTower: SquirtleTower;
    private lateinit var monument: Monument;
    private lateinit var shop: Shop;
    private lateinit var moneyView: TextView;
    private lateinit var healthView: TextView;

    private fun updateMoneyView() {
        moneyView.text = player.money.toString()
    }

    private fun updateHealthView() {
        healthView.text = monument.health.toString()
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        shop = Shop()

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

        CharmanderTower = CharmanderTower(difficulty)
        BulbasaurTower = BulbasaurTower(difficulty)
        SquirtleTower = SquirtleTower(difficulty)
        monument = Monument(difficulty)
        player = Player(difficulty)

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
        moneyView = findViewById<TextView>(R.id.startingMoney)
        updateMoneyView()

        // Displays monument health, depending on difficulty
        healthView = findViewById<TextView>(R.id.monumentHealth)
        updateHealthView()

        buyTower1.setOnClickListener {
            if (shop.buyTower(CharmanderTower, player)) {
                moneyView.text = player.money.toString()
                placeTower(CharmanderTower, locations)
            } else {
                insufficientFunds()
            }
        }
        buyTower2.setOnClickListener {
            if (shop.buyTower(SquirtleTower, player)) {
                moneyView.text = player.money.toString()
                placeTower(SquirtleTower, locations)
            } else {
                insufficientFunds()
            }
        }
        buyTower3.setOnClickListener {
            if (shop.buyTower(BulbasaurTower, player)) {
                moneyView.text = player.money.toString()
                placeTower(BulbasaurTower, locations)
            } else {
                insufficientFunds()
            }
        }


        val startRound = findViewById<ImageButton>(R.id.startRound)

        var enemyList = arrayListOf<ImageView>()

        var enemiesSpawned: Int = 0

        var rattata = findViewById<ImageView>(R.id.rattata)
        enemyList.add(rattata)
        var rattata1 = findViewById<ImageView>(R.id.rattata1)
        enemyList.add(rattata1)
        /*while (enemiesSpawned < 4) {
            var rattata = findViewById<ImageView>(R.id.rattata)
            rattata.visibility = View.INVISIBLE
            enemyList.add(rattata)
            enemiesSpawned++
        }*/

        val enemy1 = Enemy1(difficulty, enemyList)

        startRound.setOnClickListener {
            enemy1.spawnEnemies()
        }

    }

    // placement of towers functionality, can currently place in one of nine spots on screen
    fun placeTower(tower: Tower, locations: ArrayList<Location>) : Boolean {
        for (location in locations) {
            if (!(location.hasTower)) {
                location.button.visibility = View.VISIBLE
            }
        }
        for (location in locations) {
            location.button.setOnClickListener {
                location.visibilityOff(locations)
                location.hasTower = true
                placeTowerSprite(location.layout, tower.imgResId)
                shop.subtractBalance(tower, player)
                updateMoneyView()
            }
        }
        return true
    }

    // Helper function to draw sprite onto grid
    fun placeTowerSprite(view: ViewGroup, resId: Int) {
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


