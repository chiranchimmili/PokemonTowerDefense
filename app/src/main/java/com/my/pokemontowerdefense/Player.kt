package com.my.pokemontowerdefense

class Player (difficulty: String) {

    var money: Int = 0
    var score: Int = 0
    var name: String = ""

    init {
        if (difficulty == "hard") {
            money = 500;
        } else if (difficulty == "medium") {
            money = 1000;
        } else {
            money = 2000;
        }
    }
}