package com.my.pokemontowerdefense

class BulbasaurTower(difficulty: String): Tower() {
    constructor() : this("easy")

    override var imgResId = R.drawable.bulbasaur1

    init {
        imageString="@drawable/bulbasaur1";
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