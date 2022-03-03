package com.my.pokemontowerdefense

class SubTower1: Tower() {

    override var cost = 0

    override fun determineCost(): Int {
        if (difficulty == "hard") {
            cost = 500
        } else if (difficulty == "medium") {
            cost = 300
        } else {
            cost = 100
        }
        return cost
    }

}