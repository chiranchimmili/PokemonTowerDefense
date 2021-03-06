package com.my.pokemontowerdefense


//import android.os.Trace.isEnabled
//import android.widget.ImageView
//import android.widget.TextView
import androidx.test.platform.app.InstrumentationRegistry
//import kotlinx.android.synthetic.main.activity_game_screen.*
//import org.hamcrest.CoreMatchers.not
import android.widget.ImageView
import org.junit.Assert.*
//import org.junit.Before
import org.junit.Test


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class M5UnitTest {
    var charTower = CharmanderTower("hard")
    var bulbTower = BulbasaurTower("hard")
    var squirTower = SquirtleTower("hard")
    var rattataH = RattataEnemy("hard", 3)
    var haunterH = HaunterEnemy("hard", 3)
    var grimerH = GrimerEnemy("hard", 3)
    var rattataM = RattataEnemy("medium", 2)
    var rattataE = RattataEnemy("easy", 1)
    var enemyList = arrayListOf<ImageView>()
    var enemyList2 = arrayListOf<ImageView>()
    var enemyList3 = arrayListOf<ImageView>()
    val rattataEnemy = RattataEnemy("hard", enemyList.size)
    val haunterEnemy = HaunterEnemy("hard", enemyList2.size)
    val grimerEnemy = GrimerEnemy("hard", enemyList3.size)


    @Test
    fun testEnemyHPDecrease() {
        rattataH.reduceEnemyHealth()
        assertEquals(95, rattataH.hp)
        rattataH.reduceEnemyHealth()
        assertEquals(90, rattataH.hp)
        grimerH.reduceEnemyHealth()
        assertEquals(-9, grimerH.hp)
        grimerH.reduceEnemyHealth()
        assertEquals(-19, grimerH.hp)
        haunterH.reduceEnemyHealth()
        haunterH.reduceEnemyHealth()
        assertEquals(0, haunterH.hp)
    }

    @Test
    fun testEnemyGamePlayDistinction() {
        assertNotEquals(rattataH.hp, grimerH.hp)
        assertNotEquals(grimerH.hp, haunterH.hp)
        assertNotEquals(rattataH.hp, haunterH.hp)
    }

    @Test
    fun testKillMoneyDiffers() {
        assertNotEquals(rattataH.awardMoney, grimerH.awardMoney)
        assertNotEquals(rattataH.awardMoney, haunterH.awardMoney)
        assertNotEquals(grimerH.awardMoney, haunterH.awardMoney)
    }

    @Test
    fun towerBehaviorVaries() {
        assertNotEquals(charTower.cooldownTime, bulbTower.cooldownTime)
        assertNotEquals(charTower.cooldownTime, squirTower.cooldownTime)
        assertNotEquals(bulbTower.cooldownTime, squirTower.cooldownTime)
    }

    @Test
    fun testSpawnAmountVaries() {
        val rattataEnemy2 = RattataEnemy("medium", enemyList.size)
        val haunterEnemy2 = HaunterEnemy("easy", enemyList2.size)
        assertEquals(0, haunterEnemy2.amount)
        assertEquals(0, rattataEnemy2.amount)
        assertEquals(0, rattataEnemy.amount)
    }

    @Test
    fun testDistinctEnemy() {
        assertNotEquals(rattataEnemy, haunterEnemy)
        assertNotEquals(grimerEnemy, haunterEnemy)
        assertNotEquals(rattataEnemy, grimerEnemy)
    }

    @Test
    fun towerRangeSame() {
        assertEquals(charTower.towerRange, squirTower.towerRange)
        assertEquals(bulbTower.towerRange, squirTower.towerRange)
        assertEquals(charTower.towerRange, bulbTower.towerRange)
    }

    @Test
    fun checkRemoveEnemyRattata() {
        var rattataView = ImageView(null)
        rattataView.setImageResource(R.drawable.rattata8bit)
        Enemy.removeEnemy(rattataView)
        assertEquals(rattataView.visibility, 0)
    }

    @Test
    fun checkRemoveEnemyGrimer() {
        var grimerView = ImageView(null)
        grimerView.setImageResource(R.drawable.grimer8bit)
        Enemy.removeEnemy(grimerView)
        assertEquals(grimerView.visibility, 0)
    }

    @Test
    fun checkRemoveEnemyHaunter() {
        var haunterView = ImageView(null)
        haunterView.setImageResource(R.drawable.haunter8bit)
        Enemy.removeEnemy(haunterView)
        assertEquals(haunterView.visibility, 0)
    }


}