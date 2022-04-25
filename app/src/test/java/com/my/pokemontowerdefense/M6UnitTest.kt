package com.my.pokemontowerdefense

import Giratina
import junit.framework.Assert.*
import org.junit.Test

class M6UnitTest {

    val giratina = Giratina("Hard", 1)
    val grimerEnemy = GrimerEnemy("Hard", 1)
    val rattataEnemy = RattataEnemy("Hard", 1)
    val haunterEnemy = HaunterEnemy("Hard", 1)
    val charmander = CharmanderTower("Hard")
    val charmander2 = CharmanderTower("Hard")
    val squirtle = SquirtleTower("Hard")
    val bulbasaur = BulbasaurTower("Hard")
    val stats = Stats()
    val shop = Shop()
    val player = Player(null, "Hard")


    @Test
    fun testBossMoreDifficult() {
        assertTrue(giratina.hp > grimerEnemy.hp)
        assertTrue(giratina.hp > rattataEnemy.hp)
        assertTrue(giratina.hp > haunterEnemy.hp)
    }

    @Test
    fun testBossMoreDamage() {
        assertTrue(giratina.damage > grimerEnemy.damage)
        assertTrue(giratina.damage > rattataEnemy.damage)
        assertTrue(giratina.damage > haunterEnemy.damage)
    }

    @Test
    fun testBossIsDistinct() {
        assertFalse(giratina.imgResId == (grimerEnemy.imgResId))
        assertFalse(giratina.imgResId == (haunterEnemy.imgResId))
        assertFalse(giratina.imgResId == (rattataEnemy.imgResId))
    }

    @Test
    fun testUpgradeSuccess() {
        assertTrue(charmander.upgrade())
        assertTrue(bulbasaur.upgrade())
        assertTrue(squirtle.upgrade())
    }

    @Test
    fun testUpgradeGamePlayBenefit() {
        charmander.upgrade()
        assertTrue(charmander.damage > charmander2.damage)
    }

    @Test
    fun testUpgradeVisuallyDistinct() {
        charmander.upgrade()
        assertFalse(charmander.imgResId == charmander2.imgResId)
    }

    @Test
    fun testUpgradeDescriptivelyDistinct() {
        charmander.upgrade()
        assertFalse(charmander.imageString == charmander2.imageString)
    }

    @Test
    fun testUpgradeInsufficientFunds() {
        player.money = 0
        assertFalse(shop.upgradeTower(charmander, player))
    }

    @Test
    fun towerUpgradeCostsMoney() {
        assertEquals(charmander.upgradeCost, 700)
        assertEquals(bulbasaur.upgradeCost, 700)
        assertEquals(squirtle.upgradeCost, 700)
    }

    @Test
    fun testThreeStatistics() {
        assertEquals(stats.damageTaken, 0)
        assertEquals(stats.enemiesKilled, 0)
        assertEquals(stats.moneySpent, 0)
    }



}