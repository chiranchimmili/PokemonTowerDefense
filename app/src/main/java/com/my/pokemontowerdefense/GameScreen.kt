package com.my.pokemontowerdefense

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
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
    private lateinit var locations: ArrayList<Location>;

    private fun updateMoneyView() {
        moneyView.text = player.money.toString()
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
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

        shop = Shop()
        CharmanderTower = CharmanderTower(difficulty)
        BulbasaurTower = BulbasaurTower(difficulty)
        SquirtleTower = SquirtleTower(difficulty)
        player = Player(difficulty)

        val buyTower1 = findViewById<ImageButton>(R.id.buyTower1Image)
        val buyTower2 = findViewById<ImageButton>(R.id.buyTower2Image)
        val buyTower3 = findViewById<ImageButton>(R.id.buyTower3Image)

        buyTower1.setOnClickListener {
            buyTowerEvent(CharmanderTower)
        }
        buyTower2.setOnClickListener {
            buyTowerEvent(SquirtleTower)
        }
        buyTower3.setOnClickListener {
            buyTowerEvent(BulbasaurTower)
        }

        val location1 = Location(findViewById<Button>(R.id.location1button), location1relative)
        val location2 = Location(findViewById<Button>(R.id.location2button), location2relative)
        val location3 = Location(findViewById<Button>(R.id.location3button), location3relative)
        val location4 = Location(findViewById<Button>(R.id.location4button), location4relative)
        val location5 = Location(findViewById<Button>(R.id.location5button), location5relative)
        val location6 = Location(findViewById<Button>(R.id.location6button), location6relative)
        val location7 = Location(findViewById<Button>(R.id.location7button), location7relative)
        val location8 = Location(findViewById<Button>(R.id.location8button), location8relative)
        val location9 = Location(findViewById<Button>(R.id.location9button), location9relative)

        locations = arrayListOf(
            location1, location2, location3, location4, location5, location6,
            location7, location8, location9
        )

        // Displays starting money, depending on difficulty
        moneyView = findViewById<TextView>(R.id.startingMoney)
        updateMoneyView()

        monument = Monument(findViewById<TextView>(R.id.monumentHealth), difficulty)

        val startRound = findViewById<ImageButton>(R.id.startRound)
        startRound.setOnClickListener {
            startWave()
        }

    }

    fun startWave() {
        var enemyList = arrayListOf<ImageView>()
        var ratattaCount = 3

        // Create number of imageViews based on the number of enemies to be generated
        val density = Resources.getSystem().displayMetrics.density
        for (i in 1..ratattaCount) {
            var newEnemyView = ImageView(this)
            newEnemyView.layoutParams = LinearLayout.LayoutParams((50 * density).toInt(), (50 * density).toInt())
            newEnemyView.setImageResource(R.drawable.rattataremoved)
            newEnemyView.id = View.generateViewId()
            gameScreen.addView(newEnemyView)
            enemyList.add(newEnemyView)
        }

        val enemy1 = Enemy1(difficulty, enemyList)
        enemy1.spawnEnemies(monument, this@GameScreen)

        /*while (enemiesSpawned < 4) {
            var rattata = findViewById<ImageView>(R.id.rattata)
            rattata.visibility = View.INVISIBLE
            enemyList.add(rattata)
            enemiesSpawned++
        }*/
    }

    // placement of towers functionality, can currently place in one of nine spots on screen
    fun placeTower(tower: Tower) {
        for (location in locations) {
            if (!(location.hasTower)) {
                location.setVisible()
            }
            location.button.setOnClickListener {
                Location.allVisibilityOff(locations)
                location.hasTower = true
                placeTowerSprite(location.layout, tower.imgResId)
                shop.subtractBalance(tower, player)
                updateMoneyView()
            }
        }
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
        dialogBuilder.setPositiveButton("Okay") { dialog, _ ->
            dialog.cancel()
        }
        dialogBuilder.setMessage("Insufficient funds!")
        val alert = dialogBuilder.create()
        alert.show()
    }

    private fun buyTowerEvent(tower : Tower) {
        if (shop.buyTower(tower, player)) {
            placeTower(tower)
        } else {
            insufficientFunds()
        }
    }
}


