package com.my.pokemontowerdefense


import android.content.Context
import android.os.Handler
import android.text.SpannableStringBuilder
import android.widget.Button
import android.widget.ImageView
import android.graphics.Path
import android.widget.TextView
import com.my.pokemontowerdefense.ui.login.ConfigScreen
import kotlinx.android.synthetic.main.activity_config_screen.*
import kotlinx.android.synthetic.main.activity_game_screen.*
import kotlinx.android.synthetic.main.activity_game_screen.monumentHealth
import org.junit.Test
import org.junit.Rule
import org.junit.Before
import org.junit.Assert.*
import org.junit.runner.RunWith
import org.w3c.dom.Text


class M4UnitTest {

    var enemyList = arrayListOf<ImageView>()
    var enemyList2 = arrayListOf<ImageView>()
    var enemyList3 = arrayListOf<ImageView>()
    val rattataEnemy = RattataEnemy("hard", enemyList)
    val haunterEnemy = HaunterEnemy("hard", enemyList2)
    val grimerEnemy = GrimerEnemy("hard",enemyList3)

    @Test
    // M4 - Test number of enemies spawned varies by difficulty
    fun testAmountVaries() {
        val rattataEnemy2 = RattataEnemy("medium", enemyList)
        val haunterEnemy2 = HaunterEnemy("easy", enemyList2)
        assertEquals(1, haunterEnemy2.amount)
        assertEquals(2, rattataEnemy2.amount)
        assertEquals(3, rattataEnemy.amount)
    }

    @Test
    // M4 - Each enemy in the game is unique
    fun testDistinctEnemy() {
        assertNotEquals(rattataEnemy, haunterEnemy)
        assertNotEquals(grimerEnemy, haunterEnemy)
        assertNotEquals(rattataEnemy, grimerEnemy)

    }

    @Test
    // M4 - Each enemy has their health initalized by difficulty
    fun testEnemyHPInitialized(){
        assertEquals(1, rattataEnemy.hp)
        assertEquals(2, haunterEnemy.hp)
        assertEquals(3, grimerEnemy.hp)
    }

    @Test
    // M4 - Monument health is reduced
    fun testDamageInitalized() {
        assertEquals(10, rattataEnemy.damage)
        assertEquals(10, haunterEnemy.damage)
        assertEquals(10, grimerEnemy.damage)
    }

    @Test
    fun testPathIsInitiazlied() {
        assertNotNull(rattataEnemy.path)
        assertNotNull(haunterEnemy.path)
        assertNotNull(grimerEnemy.path)
    }















}