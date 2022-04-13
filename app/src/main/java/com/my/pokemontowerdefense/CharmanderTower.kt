package com.my.pokemontowerdefense

import android.animation.ObjectAnimator
import android.content.Context
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout

class CharmanderTower(difficulty: String) : Tower() {
    constructor() : this("easy")

    override var imgResId = R.drawable.charmander1
    override var cooldownTime = 2000

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

    override fun shootEnemy(
        enemy: ImageView,
        enemyClass: Enemy,
        anim: ObjectAnimator,
        context: Context,
        location: Location,
        gameScreen: ConstraintLayout,
        player: Player
    ) {

    }


}