package com.my.pokemontowerdefense

class CharmanderTower(difficulty: String) : Tower() {

    init {
        imageString="@drawable/charmander1";
        level = 1;
        if (difficulty == "easy") {
            cost = 200;
            damage = 10;
        } else if (difficulty == "medium") {
            cost = 300;
            damage = 15;
        } else {
            cost = 400;
            damage = 20;
        }
    }
    override fun update() {

    }


}