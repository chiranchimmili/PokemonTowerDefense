package com.my.pokemontowerdefense

import Giratina
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_game_screen.*
import kotlinx.coroutines.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList
import kotlin.math.floor


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
    private var level: Int = 1
    private lateinit var placedCharTower: ImageButton
    private lateinit var placedSquirtleTower: ImageButton
    private lateinit var placedBulbasaurTower: ImageButton

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
        var stats = Stats()

        shop = Shop()
        CharmanderTower = CharmanderTower(difficulty)
        BulbasaurTower = BulbasaurTower(difficulty)
        SquirtleTower = SquirtleTower(difficulty)
        player = Player(findViewById<TextView>(R.id.startingMoney), difficulty)
        player.moneyViewUpdate()

        val buyTower1 = findViewById<ImageButton>(R.id.buyTower1Image)
        val buyTower2 = findViewById<ImageButton>(R.id.buyTower2Image)
        val buyTower3 = findViewById<ImageButton>(R.id.buyTower3Image)

        val upgradeCharmanderTower = findViewById<ImageButton>(R.id.upgradeCharmander)

        val location1 = Location(findViewById<Button>(R.id.location1button), location1relative, true, false, 0, 100F, 0F, 400F, 0F)
        val location2 = Location(findViewById<Button>(R.id.location2button), location2relative, true, true, 0, 820F, 50F, 900F, 250F)
        val location3 = Location(findViewById<Button>(R.id.location3button), location3relative, false, false, 0, 500F, 200F, 800F, 500F)
        val location4 = Location(findViewById<Button>(R.id.location4button), location4relative, true, true, 0,820F, 550F, 900F, 750F)
        val location5 = Location(findViewById<Button>(R.id.location5button), location5relative, true, true, 0,820F, 800F, 990F, 1100F)
        val location6 = Location(findViewById<Button>(R.id.location6button), location6relative, false, true, 0,0F, 200F, 0F, 500F)
        val location7 = Location(findViewById<Button>(R.id.location7button), location7relative, false, false, 1,900F, 700F, 1100F, 900F)
        val location8 = Location(findViewById<Button>(R.id.location8button), location8relative, false, false, 2,1275F, 700F, 1525F, 900F)
        val location9 = Location(findViewById<Button>(R.id.location9button), location9relative, true, true, 0,1500F, 500F, 1800F, 700F)

        locations = arrayListOf(
            location1, location2, location3, location4, location5, location6,
            location7, location8, location9
        )
        // Displays starting money, depending on difficulty
        monument = Monument(findViewById<TextView>(R.id.monumentHealth), difficulty)
        monument.monumentInitHealth(stats)

        buyTower1.setOnClickListener {
            buyTowerEvent(CharmanderTower, stats)
        }
        buyTower2.setOnClickListener {
            buyTowerEvent(SquirtleTower, stats)
        }
        buyTower3.setOnClickListener {
            buyTowerEvent(BulbasaurTower, stats)
        }

        upgradeCharmanderTower.setOnClickListener{
            upgradeTower(CharmanderTower, placedCharTower, stats)
        }

        val startRound = findViewById<ImageButton>(R.id.startRound)
        startRound.setOnClickListener {
            startRound.visibility
            startWave(stats)
            level += 1
        }

    }

    fun startWave(stats : Stats) {
        startRound.isEnabled = false
        startRound.isVisible = false
        var amount = level

        val rattataEnemy = RattataEnemy(difficulty, amount)
        val grimerEnemy = GrimerEnemy(difficulty, amount)
        val haunterEnemy = HaunterEnemy(difficulty, amount)
        val giratinaEnemy = Giratina(difficulty, 1)

        CoroutineScope(Dispatchers.IO).launch {
            delay(TimeUnit.SECONDS.toMillis(floor(10 + 0.65 * (rattataEnemy.amount + grimerEnemy.amount + haunterEnemy.amount + giratinaEnemy.amount)).toLong() - 1))
            withContext(Dispatchers.Main) {
                startRound.isEnabled = true
                startRound.isVisible = true
            }
        }
        if (level == 6) {
            giratinaEnemy.spawnEnemies(monument, this@GameScreen, locations, gameScreen, player, stats)

        } else {
            rattataEnemy.spawnEnemies(monument, this@GameScreen, locations, gameScreen, player, stats)
            haunterEnemy.delayCounter += 650 * haunterEnemy.amount
            haunterEnemy.spawnEnemies(monument, this@GameScreen, locations, gameScreen, player, stats)
            grimerEnemy.delayCounter += 1300 * grimerEnemy.amount
            grimerEnemy.spawnEnemies(monument, this@GameScreen, locations, gameScreen, player, stats)
        }
    }



    // placement of towers functionality, can currently place in one of nine spots on screen
    fun placeTower(tower: Tower, stats: Stats) {
        for (location in locations) {
            if (!(location.hasTower)) {
                location.setVisible()
            }
            location.button.setOnClickListener {
                Location.allVisibilityOff(locations)
                location.hasTower = true
                location.towerReference = tower
                placeTowerSprite(location.layout, tower.imgResId)
                player.subtractMoney(tower.cost, stats)
            }
        }
    }

    // Helper function to draw sprite onto grid
    fun placeTowerSprite(view: ViewGroup, resId: Int) {
        if (resId == CharmanderTower.imgResId) {
            placedCharTower = ImageButton(this)
            placedCharTower.layoutParams = LinearLayout.LayoutParams(800, 800)
            placedCharTower.x = 20F
            placedCharTower.y = 20F
            placedCharTower.setBackgroundColor(Color.TRANSPARENT)
            placedCharTower.scaleType = ImageView.ScaleType.FIT_START
            placedCharTower.setImageResource(resId)
            //imageButton.scaleType = ImageView.ScaleType.FIT_START
            view?.addView(placedCharTower)
        } else if (resId == BulbasaurTower.imgResId) {
            placedBulbasaurTower = ImageButton(this)
            placedBulbasaurTower.layoutParams = LinearLayout.LayoutParams(800, 800)
            placedBulbasaurTower.x = 20F
            placedBulbasaurTower.y = 20F
            placedBulbasaurTower.setBackgroundColor(Color.TRANSPARENT)
            placedBulbasaurTower.scaleType = ImageView.ScaleType.FIT_START
            placedBulbasaurTower.setImageResource(resId)
            //imageButton.scaleType = ImageView.ScaleType.FIT_START
            view?.addView(placedBulbasaurTower)
        } else {
            placedSquirtleTower = ImageButton(this)
            placedSquirtleTower.layoutParams = LinearLayout.LayoutParams(800, 800)
            placedSquirtleTower.x = 20F
            placedSquirtleTower.y = 20F
            placedSquirtleTower.setBackgroundColor(Color.TRANSPARENT)
            placedSquirtleTower.scaleType = ImageView.ScaleType.FIT_START
            placedSquirtleTower.setImageResource(resId)
            //imageButton.scaleType = ImageView.ScaleType.FIT_START
            view?.addView(placedSquirtleTower)
        }

    }

    fun upgradeTower(tower: Tower, towerImage: ImageButton, stats: Stats) {
        if (tower.imgResId == R.drawable.charmander1) {
            if(shop.upgradeTower(CharmanderTower, player)) {
                tower.imgResId = R.drawable.charmeleon
                tower.imageString = "@drawable/charmeleon"
                towerImage.scaleType = ImageView.ScaleType.FIT_START
                towerImage.setImageResource(R.drawable.charmeleon)
                player.subtractMoney(tower.upgradeCost, stats)
            } else {
                insufficientFunds()
            }
        } else if (tower.imgResId == R.drawable.squirtle1) {
            tower.imgResId = R.drawable.wartortle
            tower.imageString = "@drawable/wartortle"
            towerImage.scaleType = ImageView.ScaleType.FIT_START
            towerImage.setImageResource(R.drawable.wartortle)
        } else {
            tower.imgResId = R.drawable.ivysaur
            tower.imageString = "@drawable/ivysaur"
            towerImage.scaleType = ImageView.ScaleType.FIT_START
            towerImage.setImageResource(R.drawable.ivysaur)
        }

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

    private fun buyTowerEvent(tower : Tower, stats: Stats) {
        if (shop.buyTower(tower, player)) {
            placeTower(tower, stats)
        } else {
            insufficientFunds()
        }
    }

    fun getPlayerMoney(): Int {
        return player.money
    }
    fun getLocations(): ArrayList<Location> {
        return locations
    }
}


