package com.my.pokemontowerdefense

class Shop {

    // Checks if tower can be afforded
    fun buyTower(tower: Tower, player: Player): Boolean {
        return player.money >= tower.cost
    }
    fun subtractBalance(tower: Tower, player: Player) {
        player.money -= tower.cost
    }



}