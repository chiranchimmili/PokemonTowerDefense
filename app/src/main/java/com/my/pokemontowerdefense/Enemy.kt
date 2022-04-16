package com.my.pokemontowerdefense

import android.animation.Animator
import android.animation.ObjectAnimator
import android.content.Context
import android.content.res.Resources
import android.icu.number.IntegerWidth
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import java.io.Serializable

open abstract class Enemy : Serializable{
    open var hp: Int = 0
    open var level: Int = 0
    open var amount: Int = 0
    open var damage: Int = 0
    open var delayCounter: Long = 0
    open var awardMoney: Int = 0
    open var enemyList = arrayListOf<ImageView>()
    open val enemyListHealth = hashMapOf<Int, Int>()
    open var dead: Int = 0
    open var bad: Int = 0


    abstract fun spawnEnemies(monument: Monument, context: Context, locations : ArrayList<Location>, gameScreen: ConstraintLayout, player : Player, stats: Stats)

    abstract fun reduceEnemyHealth()

    fun combat(enemyView: ImageView, player : Player) {
        if (hp >= 0) {
            reduceEnemyHealth()
        } else {
            if (enemyView.visibility == View.VISIBLE) {
                player.addMoney(awardMoney)
            }
            enemyView.visibility = View.INVISIBLE
        }
    }

    fun scanForDamage(enemy: ImageView, enemyClass: Enemy, anim: ObjectAnimator, context: Context, locations : ArrayList<Location>, gameScreen: ConstraintLayout, player : Player, monument : Monument, stats: Stats) {
        anim.addUpdateListener {
            for (location in locations) {
                if (location.hasTower) {
                    location.towerReference.shootEnemy(enemy, enemyClass, anim, context, location, gameScreen, player, monument, stats);
                }
            }
        }
    }
    companion object {
        var killed: Int = 0

        fun removeEnemy(enemyView: ImageView) {
            enemyView.visibility = View.INVISIBLE
        }
    }
}