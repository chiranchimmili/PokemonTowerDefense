package com.my.pokemontowerdefense

class Shop {

    var difficulty = GameScreen().difficulty
    var player = Player()
    var tower1 = SubTower1()

    fun buyTower(tower: Tower): String {
        if (difficulty == "hard") {
            tower1.cost = 500
        }
        if (player.money >= SubTower1().cost) {
            player.money = player.money - tower.cost
            return "Success!"
        } else {
            return "Insufficient funds"
        }
    }

}