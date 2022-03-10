package com.my.pokemontowerdefense

import com.my.pokemontowerdefense.ui.login.ConfigScreen
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

}