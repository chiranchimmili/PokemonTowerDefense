package com.my.pokemontowerdefense

class Player {
    val difficulty = GameScreen().difficulty
    val startingMoney = GameScreen().startingMoney
    var balance = startingMoney

    fun buyTower(tower: Tower): String {
        if (balance >= tower.cost) {
            balance -= tower.cost
            return ""
        } else {
            return "Insufficient funds"
        }
    }
}