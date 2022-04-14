package com.my.pokemontowerdefense

import kotlinx.android.synthetic.main.activity_game_screen.*
import org.junit.Test


import org.junit.Assert.*

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


    fun towersBoughtAfterCombat() {
        gamescreen.startWave()
        onView(withId(R.id. your_button)).check(matches(isEnabled()));
    }

}