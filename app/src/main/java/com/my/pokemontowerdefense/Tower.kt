package com.my.pokemontowerdefense

import android.animation.ObjectAnimator
import android.content.Context
import android.icu.number.IntegerWidth
import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout

open abstract class Tower {
    abstract val imgResId: Int
    open var cooldownTime = 0
    var timeStamp = System.currentTimeMillis()
    open var cost: Int = 0
        get() {
            return field
        }
        set(value) {
            field = value;
        }
    open var level: Int = 1
        get() {
            return field
        }
        set(value) {
            field = value;
        }
    open var damage: Int = 0
        get() {
            return field;
        }
        set(value) {
            field = value;
        }
    open var imageString: String = ""
        get() {
            return field;
        }
        set(value) {
            field = value;
        }
    open var location: String = ""
        get() {
            return field;
        }
        set(value) {
            field = value;
        }
    open var description: String = ""
        get() {
            return field;
        }
        set(value) {
            field = value;
        }
    abstract fun update();
    abstract fun shootEnemy(enemy: ImageView, enemyClass: Enemy, anim: ObjectAnimator, context: Context, location : Location, gameScreen: ConstraintLayout, player : Player);

    fun towerCombat(enemyView: ImageView, enemyClass: Enemy, player : Player) {
        var healthVal = enemyClass.enemyListHealth.getValue(enemyView.id)
        if (healthVal > 0) {
            if (System.currentTimeMillis() - timeStamp > cooldownTime) {
                timeStamp = System.currentTimeMillis()
                enemyClass.enemyListHealth[enemyView.id] = healthVal - damage
            }
        } else {
            if (enemyView.visibility == View.VISIBLE) {
                player.addMoney(enemyClass.awardMoney)
                enemyView.visibility = View.INVISIBLE
            }
        }

    }
}