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