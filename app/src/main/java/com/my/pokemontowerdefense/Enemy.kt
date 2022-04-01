package com.my.pokemontowerdefense

import android.content.Context
import android.widget.ImageView

open abstract class Enemy {
    open var hp: Int = 0
    open var level: Int = 0
    open var amount: Int = 0
    open var damage: Int = 0
    open var delayCounter: Long = 0
    open var awardMoney: Int = 0

    abstract fun spawnEnemies(monument: Monument, context: Context)
}