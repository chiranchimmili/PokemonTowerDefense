package com.my.pokemontowerdefense

import android.animation.ObjectAnimator
import android.content.Context
import android.content.res.Resources
import android.graphics.Path
import android.icu.number.IntegerWidth
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.animation.doOnEnd

open abstract class Tower {
    abstract val imgResId: Int
    open var cooldownTime = 0
    abstract val towerRange: Int
    val density = Resources.getSystem().displayMetrics.density
    abstract val atkResId: Int
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
    abstract fun towerCombat(enemyView: ImageView, enemyClass: Enemy, player : Player, context: Context, gameScreen: ConstraintLayout, location: Location)
}