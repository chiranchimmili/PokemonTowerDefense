package com.my.pokemontowerdefense

import android.text.SpannableStringBuilder
import android.widget.Button
import com.my.pokemontowerdefense.ui.login.ConfigScreen
import kotlinx.android.synthetic.main.activity_config_screen.*
import kotlinx.android.synthetic.main.activity_game_screen.*
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith


class M3UnitTest {

    @Test
    // M3 - Each type of tower costs differently in each difficulty
    fun testPriceVariesByDifficulty() {
        val testTower1hard = CharmanderTower("hard")
        val testTower1med = CharmanderTower("medium")
        val testTower1easy = CharmanderTower("easy")
        assertEquals(testTower1hard.cost, 400)
        assertEquals(testTower1med.cost, 300)
        assertEquals(testTower1easy.cost, 200)

        val testTower2hard = SquirtleTower("hard")
        val testTower2med = SquirtleTower("medium")
        val testTower2easy = SquirtleTower("easy")
        assertEquals(testTower2hard.cost, 400)
        assertEquals(testTower2med.cost, 300)
        assertEquals(testTower2easy.cost, 200)

        val testTower3hard = BulbasaurTower("hard")
        val testTower3med = BulbasaurTower("medium")
        val testTower3easy = BulbasaurTower("easy")
        assertEquals(testTower3hard.cost, 400)
        assertEquals(testTower3med.cost, 300)
        assertEquals(testTower3easy.cost, 200)
    }


    @Test
    // M3 - Player is prevented from making a purchase if they have insufficient funds
    fun testInsufficientFunds() {
        val testShop = Shop()
        val testPlayer = Player("hard")
        val testTower = SquirtleTower("hard")

        testShop.buyTower(testTower, testPlayer)
        assertFalse(testShop.buyTower(testTower, testPlayer))
    }


    @Test
    // M3 - Player's money should be reduced by the cost of the tower when making a purchase
    fun testCorrectSubtraction() {
        val testShop = Shop()
        val testPlayerHard = Player("hard")
        val testPlayerMed = Player("medium")
        val testPlayerEasy = Player("easy")

        val testTower3Hard = BulbasaurTower("hard")
        val testTower3Medium = BulbasaurTower("medium")
        val testTower3Easy = BulbasaurTower("easy")

        testShop.buyTower(testTower3Hard, testPlayerHard)
        assertEquals(testPlayerHard.money, 100)

        testShop.buyTower(testTower3Medium, testPlayerMed)
        assertEquals(testPlayerMed.money, 700)

        testShop.buyTower(testTower3Easy, testPlayerEasy)
        assertEquals(testPlayerEasy.money, 1800)
    }


    @Test
    // M2 - Player's starting money is different on easy, medium, and hard difficulty
    fun testVaryingStartingMoney() {
        val testPlayerHard = Player("hard")
        val testPlayerMedium = Player("medium")
        val testPlayerEasy = Player("easy")
        assertEquals(testPlayerHard.money, 500)
        assertEquals(testPlayerMedium.money, 1000)
        assertEquals(testPlayerEasy.money, 2000)
    }


    @Test
    // M2 - Player's name shouldn't be empty or only spaces
    fun testCheckNameInvalid() {

        assertEquals(ConfigScreen.checkNameInvalid(""), true)
        assertEquals(ConfigScreen.checkNameInvalid(" "), true)
        assertEquals(ConfigScreen.checkNameInvalid("   "), true)
        assertEquals(ConfigScreen.checkNameInvalid("    "), true)
    }


    @Test
    // M3 - Each tower has a different damage based on difficulty
    fun testTowerDamage() {
        var charTowerE = CharmanderTower("easy");
        var bulbTowerE = BulbasaurTower("easy");
        var squirTowerE = SquirtleTower("easy");

        var charTowerM = CharmanderTower("medium");
        var bulbTowerM = BulbasaurTower("medium");
        var squirTowerM = SquirtleTower("medium");

        var charTowerH = CharmanderTower("hard");
        var bulbTowerH = BulbasaurTower("hard");
        var squirTowerH = SquirtleTower("hard");

        // Checking that CharizardTower has different damage based on difficulty
        assertNotEquals(charTowerE.damage, charTowerM.damage)
        assertNotEquals(charTowerE.damage, charTowerH.damage)
        assertNotEquals(charTowerM.damage, charTowerH.damage)

        // Checking that BulbasaurTower has different damage based on difficulty
        assertNotEquals(bulbTowerE.damage, bulbTowerM.damage)
        assertNotEquals(bulbTowerE.damage, bulbTowerH.damage)
        assertNotEquals(bulbTowerM.damage, bulbTowerH.damage)

        // Checking that SquirtleTower has different damage based on difficulty
        assertNotEquals(squirTowerE.damage, squirTowerM.damage)
        assertNotEquals(squirTowerE.damage, squirTowerH.damage)
        assertNotEquals(squirTowerM.damage, squirTowerH.damage)
    }


    @Test
    // M3 - The monument health varies based on if the difficulty is easy, medium, or hard
    fun testMonumentHealth() {
        var monumentE = Monument("easy")
        var monumentM = Monument("medium")
        var monumentH = Monument("hard")

        assertNotEquals(monumentE.health, monumentM.health)
        assertNotEquals(monumentE.health, monumentH.health)
        assertNotEquals(monumentM.health, monumentH.health)
    }


    @Test
    // M3 - Each tower should begin at level one when first initialized
    fun testTowerLevelInit() {
        val charmTower = CharmanderTower("hard")
        val bulbTower = BulbasaurTower("hard")
        val squirtTower = SquirtleTower("hard")

        assertEquals(charmTower.level, 1)
        assertEquals(bulbTower.level, 1)
        assertEquals(squirtTower.level, 1)
    }


    @Test
    // M3 - Player's score should begin at zero for each difficulty
    fun testScoreInit() {
        val testPlayerHard = Player("hard")
        assertEquals(testPlayerHard.score, 0)
        val testPlayerMed = Player("medium")
        assertEquals(testPlayerMed.score, 0)
        val testPlayerEasy = Player("easy")
        assertEquals(testPlayerEasy.score, 0)
    }

    @Test
    // M3 - Each tower in the game is unique
    fun testDistinctTower() {
        val charmTower = CharmanderTower("hard")
        val bulbTower = BulbasaurTower("hard")
        val squirtTower = SquirtleTower("hard")

        assertNotEquals(charmTower, bulbTower)
        assertNotEquals(bulbTower, squirtTower)
        assertNotEquals(squirtTower, charmTower)

    }
}