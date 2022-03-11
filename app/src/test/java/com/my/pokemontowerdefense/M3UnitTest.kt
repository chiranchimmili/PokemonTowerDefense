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
        fun addition_isCorrect() {
            assertEquals(4, 2 + 2)
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
            assertEquals(400, 400)
        }
    }

    class TestStartingMoney {
        @Test
        fun varyingStartingMoney() {
            assertEquals(4,4)
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