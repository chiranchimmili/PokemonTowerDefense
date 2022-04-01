package com.my.pokemontowerdefense


import android.widget.ImageView
import org.junit.Test
import org.junit.Assert.*


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
    // M4 - Each enemy has their health initialized by difficulty
    fun testEnemyHPInitialized(){
        assertEquals(1, rattataEnemy.hp)
        assertEquals(2, haunterEnemy.hp)
        assertEquals(3, grimerEnemy.hp)
    }

    @Test
    // M4 - Monument health is reduced properly
    fun testDamageInitialized() {
        assertEquals(10, rattataEnemy.damage)
        assertEquals(10, haunterEnemy.damage)
        assertEquals(10, grimerEnemy.damage)
    }

    @Test
    // M4 - The enemy paths exist and are valid
    fun testPathIsInitialized() {
        assertNotNull(rattataEnemy.path)
        assertNotNull(haunterEnemy.path)
        assertNotNull(grimerEnemy.path)
    }


    @Test
    // M4 - Monument's gameIsOver variable is true when monument runs out of health
    fun testGameOver() {
        var monument = Monument(null, "hard")
        print(monument)
        monument.reduceMonumentHealth(null)
        monument.reduceMonumentHealth(null)
        monument.reduceMonumentHealth(null)
        monument.reduceMonumentHealth(null)
        monument.reduceMonumentHealth(null)
        assertEquals(true,monument.gameIsOver)
    }

    @Test
    // M4 - The enemy levels are initialized properly upon instantiation
    fun testLevelIsInitialized() {
        assertEquals(1, rattataEnemy.level)
        assertEquals(1, haunterEnemy.level)
        assertEquals(1, grimerEnemy.level)
    }

}