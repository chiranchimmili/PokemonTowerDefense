package com.my.pokemontowerdefense

class Shop {

    fun buyTower(tower: Tower, player: Player): Boolean {
        return if (player.money >= tower.cost) {
            player.money -= tower.cost
            true
        } else {
            false
        }
    }

}