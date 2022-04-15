package com.my.pokemontowerdefense


import android.os.Trace.isEnabled
import android.widget.ImageView
import android.widget.TextView
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.android.synthetic.main.activity_game_screen.*
import org.hamcrest.CoreMatchers.not
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class M5UnitTest {
    var gamescreen = GameScreen()
    var charTower = CharmanderTower("hard")
    var bulbTower = BulbasaurTower("hard")
    var squirTower = SquirtleTower("hard")
    var rattataH = RattataEnemy("hard", 3)
    var haunterH = HaunterEnemy("hard", 3)
    var grimerH = GrimerEnemy("hard", 3)
    var rattataM = RattataEnemy("medium", 2)
    var rattataE = RattataEnemy("easy", 1)


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
    fun towersBoughtAfterCombat() {
        gamescreen.startWave()
        Espresso.onView(withId(R.id.buyTower1Image)).check(matches(ViewMatchers.isEnabled()));
        Espresso.onView(withId(R.id.buyTower2Image)).check(matches(ViewMatchers.isEnabled()));
        Espresso.onView(withId(R.id.buyTower3Image)).check(matches(ViewMatchers.isEnabled()));
    }

    @Test
    fun startButtonVaries() {
        gamescreen.startWave()
        Espresso.onView(withId(R.id.startRound)).check(matches(not(ViewMatchers.isEnabled())));
    }

    @Test
    fun towerRangeSame() {
        assertEquals(charTower.towerRange, squirTower.towerRange)
        assertEquals(bulbTower.towerRange, squirTower.towerRange)
        assertEquals(charTower.towerRange, bulbTower.towerRange)
    }

    @Test
    fun checkRemoveEnemyRattata() {
        var rattataView = ImageView(InstrumentationRegistry.getInstrumentation().context)
        rattataView.setImageResource(R.drawable.rattata8bit)
        Enemy.removeEnemy(rattataView)
        assertEquals(rattataView.visibility, false)
    }

    @Test
    fun checkRemoveEnemyGrimer() {
        var grimerView = ImageView(InstrumentationRegistry.getInstrumentation().context)
        grimerView.setImageResource(R.drawable.grimer8bit)
        Enemy.removeEnemy(grimerView)
        assertEquals(grimerView.visibility, false)
    }

    @Test
    fun checkRemoveEnemyHaunter() {
        var haunterView = ImageView(InstrumentationRegistry.getInstrumentation().context)
        haunterView.setImageResource(R.drawable.haunter8bit)
        Enemy.removeEnemy(haunterView)
        assertEquals(haunterView.visibility, false)
    }



}