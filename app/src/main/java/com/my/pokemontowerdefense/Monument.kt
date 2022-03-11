package com.my.pokemontowerdefense

class Monument(difficulty: String) {
    var health: Int = 0
        get() {
            return field
        }
        set(field) {
            health = field;
        }
    init {
        if (difficulty == "hard") {
            health = 50;
        } else if (difficulty == "medium") {
            health = 100;
        } else {
            health = 200;
        }
    }

}