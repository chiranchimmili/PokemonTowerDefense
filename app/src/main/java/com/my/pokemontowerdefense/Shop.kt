package com.my.pokemontowerdefense

class Shop {

    val difficulty = GameScreen().difficulty

    fun buyTower(tower: Tower, player: Player): Boolean {
        return if (player.money >= tower.cost) {
            player.money -= tower.cost
            true
        } else {
            false
        }
    }

}