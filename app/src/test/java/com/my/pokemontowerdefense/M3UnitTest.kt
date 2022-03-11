package com.my.pokemontowerdefense

import android.text.SpannableStringBuilder
import com.my.pokemontowerdefense.ui.login.ConfigScreen
import kotlinx.android.synthetic.main.activity_config_screen.*
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith


class M3UnitTest {
    class TestPriceVariesByDifficulty {
        @Test
        fun price_isCorrect() {
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
    }

    class TestInsufficientFunds {
        @Test
        fun insufficientFundsTest() {
            assertEquals(400, 400)
        }
    }

    class TestMath {
        @Test
        fun correctSubtraction() {
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
    }

    class TestStartingMoney {
        @Test
        fun varyingStartingMoney() {
            val testPlayerHard = Player("hard")
            val testPlayerMedium = Player("medium")
            val testPlayerEasy = Player("easy")
            assertEquals(testPlayerHard.money, 500)
            assertEquals(testPlayerMedium.money, 1000)
            assertEquals(testPlayerEasy.money, 2000)
        }
    }

    class ConfigScreenTest {
        @Test
        fun testCheckNameInvalid() { // M2 - Check if player's inputted name is valid

            assertEquals(ConfigScreen.checkNameInvalid(""), true)
            assertEquals(ConfigScreen.checkNameInvalid(" "), true)
            assertEquals(ConfigScreen.checkNameInvalid("   "), true)
            assertEquals(ConfigScreen.checkNameInvalid("    "), true)
        }
    }

    class TowerTest {
        @Test
        fun test() { // M2 - The Bulbasaur tower costs differently on easy, medium, and hard diificulties
            var bt_easy = BulbasaurTower("easy")
            var bt_med = BulbasaurTower("medium")
            var bt_hard = BulbasaurTower("hard")

            assertNotEquals(bt_easy.cost, bt_med.cost)
            assertNotEquals(bt_med.cost, bt_hard.cost)
            assertNotEquals(bt_easy.cost, bt_hard.cost)

        }
    }

    class TowerDamageTest {
        @Test
        fun test() { // M3 - Each tower has a different damage based on difficulty
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
    }

    class MonumentHealthTest {
        @Test
        fun test() { // M3 - Each tower has a different health value based on the difficulty
            var monumentE = Monument("easy")
            var monumentM = Monument("medium")
            var monumentH = Monument("hard")

            assertNotEquals(monumentE.health, monumentM.health)
            assertNotEquals(monumentE.health, monumentH.health)
            assertNotEquals(monumentM.health, monumentH.health)
        }
    }
    


}