package com.my.pokemontowerdefense

import android.content.Context
import android.content.res.Resources
import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout

open abstract class Enemy {
    open var hp: Int = 0
    open var level: Int = 0
    open var amount: Int = 0
    open var damage: Int = 0
    open var delayCounter: Long = 0
    open var awardMoney: Int = 0
    open val density = Resources.getSystem().displayMetrics.density
    open var enemyList = arrayListOf<ImageView>()


    abstract fun spawnEnemies(monument: Monument, context: Context, locations : ArrayList<Location>, gameScreen: ConstraintLayout)

    abstract fun reduceEnemyHealth()

    fun combat(enemy: ImageView) {
        if (hp >= 0) {
            reduceEnemyHealth()
        } else {
            enemy.visibility = View.INVISIBLE
        }
    }
}