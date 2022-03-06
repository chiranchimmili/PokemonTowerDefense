package com.my.pokemontowerdefense

class SubTower1: Tower() {

    override var cost: Int = 0

    fun determineCost(): Int {
        if (GameScreen().difficulty == "hard") {
            return 500
        }
        return 0
    }

}