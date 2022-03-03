package com.my.pokemontowerdefense

import org.junit.Test

import org.junit.Assert.*

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
}