package com.my.pokemontowerdefense

import Giratina
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.graphics.Color
import android.media.Image
import android.os.Bundle
import android.view.View
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
    private lateinit var currentTower : Tower
    private var round: Int = 1
    private var placedTowerList = arrayListOf<ImageButton>()
    private var towers = arrayListOf<Tower>()

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

        val upgradeButton = findViewById<Button>(R.id.upgradeButton)
        val upgradeMenu = findViewById<LinearLayout>(R.id.upgradeMenu)

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
        upgradeButton.setOnClickListener {
            upgradeTower(currentTower, stats)
            upgradeMenu.visibility = View.GONE
        }


        val startRound = findViewById<ImageButton>(R.id.startRound)
        startRound.setOnClickListener {
            startRound.visibility
            startWave(stats)
            round += 1
        }

    }

    fun startWave(stats : Stats) {
        startRound.isEnabled = false
        startRound.isVisible = false
        var amount = round

        val rattataEnemy = RattataEnemy(difficulty, amount)
        val grimerEnemy = GrimerEnemy(difficulty, amount)
        val haunterEnemy = HaunterEnemy(difficulty, amount)
        val giratinaEnemy = Giratina(difficulty, 1)

        CoroutineScope(Dispatchers.IO).launch {
            delay(TimeUnit.SECONDS.toMillis(floor(7 + 0.65 * (rattataEnemy.amount + grimerEnemy.amount + haunterEnemy.amount + giratinaEnemy.amount)).toLong() - 1))
            withContext(Dispatchers.Main) {
                startRound.isEnabled = true
                startRound.isVisible = true
            }
        }
        if (round == 1) {
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
                placeTowerSprite(location.layout, tower.imgResId, tower)
                player.subtractMoney(tower.cost, stats)
            }
        }
    }

    // Helper function to draw sprite onto grid
    fun placeTowerSprite(view: ViewGroup, resId: Int, tower : Tower) {
        val imageButton = ImageButton(this)
        imageButton.layoutParams = LinearLayout.LayoutParams(800, 800)
        imageButton.x = 20F
        imageButton.y = 20F
        imageButton.setBackgroundColor(Color.TRANSPARENT)
        imageButton.setImageResource(tower.imgResId)
        imageButton.scaleType = ImageView.ScaleType.FIT_START
        if (resId == R.drawable.charmander1) {
            imageButton.tag = 1
        } else if (resId == R.drawable.bulbasaur1) {
            imageButton.tag = 2
        } else if (resId == R.drawable.squirtle1){
            imageButton.tag = 3
        } else if (resId == R.drawable.charmeleon) {
            imageButton.tag = 4
        } else if (resId == R.drawable.ivysaur1) {
            imageButton.tag = 5
        } else if (resId == R.drawable.wartortle) {
            imageButton.tag = 6
        }
        view?.addView(imageButton)
        tower.image = imageButton
        imageButton.setOnClickListener { v: View ->
            for (t in towers) {
                if (t.image == v) {
                    currentTower = t
                }
            }
            upgradeMenu.visibility = View.VISIBLE
        }
        towers.add(tower)
        placedTowerList.add(imageButton)
    }

    fun upgradeTower(tower: Tower, stats: Stats) {
        if(shop.upgradeTower(tower, player)) {
            if (tower.upgrade()) {
                tower.image?.scaleType = ImageView.ScaleType.FIT_START
                if (tower is CharmanderTower) {
                    if (tower.image?.tag == 1) {
                        tower.image?.setImageResource(R.drawable.charmeleon)
                        tower.image?.tag = 4
                    } else if (tower.image?.tag == 4) {
                        tower.image?.setImageResource(R.drawable.charizard_remove)
                    }
                } else if (tower is SquirtleTower) {
                    if (tower.image?.tag == 3) {
                        tower.image?.setImageResource(R.drawable.wartortle)
                        tower.image?.tag = 6
                    } else if (tower.image?.tag == 6) {
                        tower.image?.setImageResource(R.drawable.blastoise_remove)
                    }
                } else if (tower is BulbasaurTower) {
                    if (tower.image?.tag == 2) {
                        tower.image?.setImageResource(R.drawable.ivysaur1)
                        tower.image?.tag = 5
                    } else if (tower.image?.tag == 5) {
                        tower.image?.setImageResource(R.drawable.venusaur_remove)
                    }
                }
                player.subtractMoney(tower.upgradeCost, stats)

            } else {
                tower.unabletoUpgrade(this)
            }
        } else {
            if (tower.level == 3) {
                tower.unabletoUpgrade(this)
            } else {
                insufficientFunds()
            }
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


