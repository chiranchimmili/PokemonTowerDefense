package com.my.pokemontowerdefense

import android.animation.ObjectAnimator
import android.content.Context
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import kotlin.math.sqrt

class BulbasaurTower(difficulty: String): Tower() {
    constructor() : this("easy")

    override var imgResId = R.drawable.bulbasaur1
    override var cooldownTime = 100
    override var atkResId = R.drawable.razor_leaf

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

    override fun shootEnemy(
        enemy: ImageView,
        enemyClass: Enemy,
        anim: ObjectAnimator,
        context: Context,
        location: Location,
        gameScreen: ConstraintLayout,
        player: Player
    ) {
        var point = IntArray(2)
        location.buttonLocation.getLocationOnScreen(point)
        val (buttonX, buttonY) = point

        point = IntArray(2)
        enemy.getLocationOnScreen(point)
        val (enemyX, enemyY) = point

        // If the distance between the button and the bulbasaur is small enough
        val _xdif = (buttonX-enemyX)
        val _ydif = (buttonY-enemyY)
        val distance = sqrt(((_xdif*_xdif) + (_ydif*_ydif)).toDouble())

        println("The distance is $distance")
        if (distance < 500) {
            towerCombat(enemy, enemyClass, player, context, gameScreen, location)
        }

    }
}